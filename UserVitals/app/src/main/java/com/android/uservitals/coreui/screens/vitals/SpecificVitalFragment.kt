package com.android.uservitals.coreui.screens.vitals

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.android.uservitals.R
import com.android.uservitals.coreui.BaseDaggerFragment
import com.android.uservitals.coreui.UiSignal
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class SpecificVitalFragment : BaseDaggerFragment() {
    companion object {
        const val TITLE = "chartTitle"
    }

    override fun getLayout(): Int {
        return R.layout.chart_layout
    }

    override fun onViewCreationCompleted(view: View) {
        val chart = view.findViewById<BarChart>(R.id.chart)

        val chartData = BarData(getYAxisValues())
        chart.data = chartData
        chart.invalidate()
    }

    private fun getYAxisValues(): IBarDataSet? {
        val listOfBarEntries = arrayListOf<BarEntry>(
            BarEntry(1f, 80.0f),
            BarEntry(2f, 83.0f),
            BarEntry(3f, 90.0f),
            BarEntry(4f, 70.0f),
            BarEntry(5f, 91.0f)
        )

        return BarDataSet(listOfBarEntries, "Weight")

    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return MutableLiveData(UiSignal.Success(null))
    }

}