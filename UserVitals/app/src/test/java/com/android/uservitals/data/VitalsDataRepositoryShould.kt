package com.android.uservitals.data

import com.android.uservitals.domain.AllVitals
import com.android.uservitals.domain.DataMapper
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class VitalsDataRepositoryShould {
    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var retrofit: VitalRemoteService

    @Mock
    private lateinit var mapper: DataMapper<UserVitals, AllVitals>

    private lateinit var vitalsDataRepository: VitalsDataRepository

    @Before
    fun setUp() {
        vitalsDataRepository = VitalsDataRepository(retrofit, mapper)

    }

    @Test
    fun `return AllVital when api success`() = runBlocking {

        val response = Mockito.mock(UserVitals::class.java)
        val mappedModel = Mockito.mock(AllVitals::class.java)
        whenever(retrofit.fetchVitals()).thenReturn(response)
        whenever(mapper.transform(response)).thenReturn(mappedModel)
        val model = vitalsDataRepository.fetchVitals()

        Assert.assertNotNull(model)
    }
}