package com.android.uservitals.coreui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val liveData = MutableLiveData<UiSignal>()

    protected abstract fun getUiState(): MutableLiveData<UiSignal>
}