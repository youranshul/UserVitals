package com.android.uservitals.coreui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.uservitals.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseDaggerFragment : DaggerFragment() {

    private var progressBar: ProgressBar? = null

    private var errorView: TextView? = null

    abstract fun getLayout(): Int

    abstract fun onViewCreationCompleted(view: View)

    abstract fun getUiSignalLiveData(): MutableLiveData<UiSignal>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreationCompleted(view)
        bindUiSignalLiveData()
    }

    private fun bindUiSignalLiveData() {
        getUiSignalLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                UiSignal.LOADING_VISIBLE -> progressBar?.visibility = View.VISIBLE
                UiSignal.LOADING_GONE -> progressBar?.visibility = View.GONE
                UiSignal.ERROR_MSG -> errorView?.visibility = View.VISIBLE
                UiSignal.NO_INTERNET -> networkGone()
                UiSignal.INTERNET_AVAILABLE -> networkAvailable()
                else -> progressBar?.visibility = View.GONE
            }
        })
    }

    private fun networkAvailable() {
        view?.let {
            Snackbar.make(
                it, resources.getString(R.string.network_available), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun networkGone() {
        view?.let {
            Snackbar.make(it, resources.getString(R.string.no_network), Snackbar.LENGTH_INDEFINITE)
                .show()
        }
    }
}