package com.android.uservitals.coreui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val uiSignalLiveData = MutableLiveData<UiSignal>()


    abstract fun provideUiSignalLiveData(): MutableLiveData<UiSignal>

    override fun onCleared() {
        super.onCleared()
    }
}