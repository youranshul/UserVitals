package com.android.uservitals.domain.specificvital

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.uservitals.coreui.BaseViewModel
import com.android.uservitals.coreui.UiSignal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpecificVitalViewModel @Inject constructor(
    private val specificVitalService: SpecificVitalService,
    private val dispatcher: CoroutineDispatcher
) :
    BaseViewModel() {

    public override fun getUiState(): MutableLiveData<UiSignal> {
        return liveData
    }

    fun fetchSpecificVital(type: String) {
        liveData.value = UiSignal.loading
        viewModelScope.launch(dispatcher) {

            val result = kotlin.runCatching {
                specificVitalService.fetchSpecificVital(type)
            }
            result.onSuccess {
                liveData.postValue(UiSignal.Success(it))

            }.onFailure {
                liveData.postValue(UiSignal.Failure)
            }
        }
    }


}