package com.android.uservitals.data.specificvital

import com.android.uservitals.data.UserVitals
import com.android.uservitals.data.VitalRemoteService
import com.android.uservitals.domain.ExtendedMapper
import com.android.uservitals.domain.specificvital.SpecificVital
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SpecificVitalRepositoryShould {

    private val retrofit: VitalRemoteService = mock()

    private val mapper: ExtendedMapper<String, UserVitals, SpecificVital> = mock()

    private lateinit var specificVitalRepo: SpecificVitalRepository

    private val TYPE = "Weight"

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        specificVitalRepo = SpecificVitalRepository(retrofit, mapper)
    }


    @Test
    fun `verify if given type is set in mapper`() = runBlocking {
        specificVitalRepo.fetchSpecificVital(TYPE)

        verify(mapper).setValue(TYPE)
    }

    @Test
    fun `return SpecificVital for given type when api is success`() = runBlocking {
        val response = Mockito.mock(UserVitals::class.java)
        val mappedModel = Mockito.mock(SpecificVital::class.java)
        whenever(retrofit.fetchVitals()).thenReturn(response)
        whenever(mapper.transform(response)).thenReturn(mappedModel)
        val model = specificVitalRepo.fetchSpecificVital(TYPE)

        Assert.assertNotNull(model)
    }
}