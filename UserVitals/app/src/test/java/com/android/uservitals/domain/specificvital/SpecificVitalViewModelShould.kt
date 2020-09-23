package com.android.uservitals.domain.specificvital

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.uservitals.coreui.UiSignal
import com.android.uservitals.util.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SpecificVitalViewModelShould {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val service: SpecificVitalService = mock()

    private lateinit var viewModel: SpecificVitalViewModel

    private val TYPE = "testType"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SpecificVitalViewModel(
            service,
            coroutineTestRule.testDispatcher
        )
    }

    @Test
    fun `show loading while fetching vitals`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            coroutineTestRule.testDispatcher.pauseDispatcher()
            viewModel.fetchSpecificVital(TYPE)
            Assert.assertEquals(UiSignal.loading, viewModel.getUiState().value)
            coroutineTestRule.testDispatcher.resumeDispatcher()
        }
    }

    @Test
    fun `handle success while fetching vitals is successful`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            coroutineTestRule.testDispatcher.pauseDispatcher()
            viewModel.fetchSpecificVital(TYPE)

            coroutineTestRule.testDispatcher.resumeDispatcher()

            Assert.assertEquals(viewModel.getUiState().value, UiSignal.Success(null))
        }
    }

    @Test
    fun `handle error while fetching vitals is successful`() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(service.fetchSpecificVital(TYPE)).thenThrow(RuntimeException())
            coroutineTestRule.testDispatcher.pauseDispatcher()

            viewModel.fetchSpecificVital(TYPE)

            coroutineTestRule.testDispatcher.resumeDispatcher()

            Assert.assertEquals(viewModel.getUiState().value, UiSignal.Failure)

        }
    }
}