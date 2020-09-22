package com.android.uservitals.coreui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    protected val uiSignalLiveData = MutableLiveData<UiSignal>()

    protected val compositeDisposable = CompositeDisposable()

    abstract fun provideUiSignalLiveData(): MutableLiveData<UiSignal>

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    protected fun <T> getSingleObserver(
        success: (t: T) -> Unit,
        error: (e: Throwable) -> Unit = {}
    ): SingleObserver<T> {
        return object : SingleObserver<T> {
            override fun onSuccess(t: T) {
                uiSignalLiveData.value = UiSignal.LOADING_GONE
                success(t)
            }

            override fun onSubscribe(d: Disposable) {
                uiSignalLiveData.value = UiSignal.LOADING_VISIBLE
                compositeDisposable.add(d)
            }

            override fun onError(e: Throwable) {
                uiSignalLiveData.value = UiSignal.LOADING_GONE
                uiSignalLiveData.value = UiSignal.ERROR_MSG
                e.printStackTrace()
                error(e)
            }
        }
    }
}