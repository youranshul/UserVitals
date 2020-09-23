package com.android.uservitals.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.uservitals.coreui.UiSignal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserVitalsViewModel @Inject constructor(
    private val vitalsFetchService: VitalsFetchService,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val liveData = MutableLiveData<UiSignal>()


    fun getUiState(): MutableLiveData<UiSignal> = liveData

    fun fetchVitals() {
        liveData.value = UiSignal.loading
        viewModelScope.launch(dispatcher) {

            val result = kotlin.runCatching {
                vitalsFetchService.fetchVitals()
            }
            result.onSuccess {
                liveData.postValue(UiSignal.Success(it))

            }.onFailure {
                liveData.postValue(UiSignal.Failure)
            }
        }
    }
}
