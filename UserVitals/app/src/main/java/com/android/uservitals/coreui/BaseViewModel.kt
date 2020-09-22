package com.android.uservitals.coreui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun provideUiSignalLiveData(): MutableLiveData<UiSignal>

    override fun onCleared() {
        super.onCleared()
    }
}