package com.android.uservitals.coreui.screens.vitalcharts

import com.android.uservitals.domain.specificvital.SpecificVital
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.lang.ref.WeakReference

class ChartDisplayManager(chartView: BarChart, private val type: String) {

    private val weakReference = WeakReference<BarChart>(chartView)

    private lateinit var data: SpecificVital

    private val months =
        arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    fun setData(data: SpecificVital) {
        this.data = data
        val barchartData = getXAndYAxisData()
        val barData = BarData(barchartData)
        weakReference.get()?.data = barData
        setValueFormatter()
        weakReference.get()?.invalidate()
    }

    private fun setValueFormatter() {
        val xAxis = weakReference.get()?.xAxis
        xAxis?.valueFormatter =
            IAxisValueFormatter { key, axis ->
                val idx = key.toInt()
                val splittedVal = data.dates[idx].split("/")
                val date = splittedVal[0]
                val month = months[splittedVal[1].toInt() - 1]
                "$date $month"
            }
    }

    private fun getXAndYAxisData(): IBarDataSet? {
        val listOfBarEntries = arrayListOf<BarEntry>()
        for (i in data.values.indices) {
            val key: Float = i.toFloat()
            val value: Float = data.values[i].toFloat()
            val entry = BarEntry(key, value)
            listOfBarEntries.add(entry)
        }

        return BarDataSet(listOfBarEntries, type)
    }
}
