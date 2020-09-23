package com.android.uservitals.coreui.screens.vitals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.uservitals.coreui.UiSignal
import com.android.uservitals.domain.vitals.UserVitalsViewModel
import com.android.uservitals.domain.vitals.VitalsFetchService
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
class UserVitalsViewModelShould {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val service: VitalsFetchService = mock()

    private lateinit var viewModel: UserVitalsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserVitalsViewModel(
            service,
            coroutineTestRule.testDispatcher
        )
    }

    @Test
    fun `show loading while fetching vitals`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            coroutineTestRule.testDispatcher.pauseDispatcher()
            viewModel.fetchVitals()
            Assert.assertEquals(UiSignal.loading, viewModel.getUiState().value)
            coroutineTestRule.testDispatcher.resumeDispatcher()
        }
    }

    @Test
    fun `handle success while fetching vitals is successful`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            coroutineTestRule.testDispatcher.pauseDispatcher()
            viewModel.fetchVitals()

            coroutineTestRule.testDispatcher.resumeDispatcher()

            Assert.assertEquals(viewModel.getUiState().value, UiSignal.Success(null))
        }
    }

    @Test
    fun `handle error while fetching vitals is successful`() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(service.fetchVitals()).thenThrow(RuntimeException())
            coroutineTestRule.testDispatcher.pauseDispatcher()

            viewModel.fetchVitals()

            coroutineTestRule.testDispatcher.resumeDispatcher()

            Assert.assertEquals(viewModel.getUiState().value, UiSignal.Failure)

        }
    }
}