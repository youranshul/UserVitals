package com.android.uservitals.coreui.screens.vitals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.uservitals.R
import com.android.uservitals.domain.vitals.Vitals

class VitalsAdapter(
    private val listOfVitals: List<Vitals>,
    private val callback: OnItemClickListener
) : RecyclerView.Adapter<VitalsAdapter.VitalsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val holder = VitalsViewHolder(view)
        holder.itemView.setOnClickListener { onClickVital(holder.adapterPosition) }
        return holder
    }

    private fun onClickVital(adapterPosition: Int) {
        callback.onItemClicked(listOfVitals[adapterPosition].type)
    }

    override fun getItemCount(): Int = listOfVitals.size

    override fun onBindViewHolder(holder: VitalsViewHolder, position: Int) {
        holder.bind(listOfVitals[position])
    }

    inner class VitalsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val type = itemView.findViewById<TextView>(R.id.type)
        private val maxVal = itemView.findViewById<TextView>(R.id.maxVal)
        private val date = itemView.findViewById<TextView>(R.id.date)

        fun bind(vitals: Vitals) {
            type.text = vitals.type
            maxVal.text = "${vitals.maxValue} ${vitals.unit}"
            date.text = vitals.date
        }
    }

}

interface OnItemClickListener {

    fun onItemClicked(type: String)
}