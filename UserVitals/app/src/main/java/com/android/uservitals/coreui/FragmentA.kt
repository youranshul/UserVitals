package com.android.uservitals.coreui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.android.uservitals.R

class FragmentA : BaseDaggerFragment() {
    override fun getLayout(): Int {
        return R.layout.user_vitals
    }

    override fun onViewCreationCompleted(view: View) {
            Log.i("Ansh","coming")
    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return MutableLiveData(UiSignal.LOADING_GONE)
    }
}