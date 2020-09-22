package com.android.uservitals.coreui

sealed class UiSignal {


    object loading : UiSignal()
    data class Success<T>(val data: T) : UiSignal()
    data class Failure(val exception: String) : UiSignal()

}