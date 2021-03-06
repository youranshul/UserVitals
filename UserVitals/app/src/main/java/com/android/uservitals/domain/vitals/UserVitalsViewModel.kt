package com.android.uservitals.domain.vitals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.uservitals.coreui.BaseViewModel
import com.android.uservitals.coreui.UiSignal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserVitalsViewModel @Inject constructor(
    private val vitalsFetchService: VitalsFetchService,
    private val dispatcher: CoroutineDispatcher
) :
    BaseViewModel() {

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

    public override fun getUiState(): MutableLiveData<UiSignal> {
        return liveData
    }
}
