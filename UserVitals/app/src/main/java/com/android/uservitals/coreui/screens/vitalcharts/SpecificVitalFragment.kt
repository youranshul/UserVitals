package com.android.uservitals.coreui.screens.vitalcharts

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.uservitals.R
import com.android.uservitals.coreui.BaseDaggerFragment
import com.android.uservitals.coreui.UiSignal
import com.android.uservitals.domain.specificvital.SpecificVital
import com.android.uservitals.domain.specificvital.SpecificVitalViewModel
import com.github.mikephil.charting.charts.BarChart
import javax.inject.Inject

class SpecificVitalFragment : BaseDaggerFragment() {
    companion object {
        const val TITLE = "chartTitle"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SpecificVitalViewModel
    private lateinit var displayManager: ChartDisplayManager

    override fun getLayout(): Int {
        return R.layout.chart_layout
    }

    override fun onViewCreationCompleted(view: View) {
        val type = arguments?.get(TITLE) as String
        activity?.title = type
        val chart = view.findViewById<BarChart>(R.id.chart)
        displayManager = ChartDisplayManager(chart, type)
        initViewModel(type)
    }

    private fun initViewModel(type: String) {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SpecificVitalViewModel::class.java)

        viewModel.getUiState().observe(viewLifecycleOwner, Observer {
            if (it is UiSignal.Success<*>) {
                val vital = it.data as SpecificVital
                displayManager.setData(vital)

            }
        })

        viewModel.fetchSpecificVital(type)
    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return viewModel.getUiState()
    }

}