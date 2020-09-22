package com.android.uservitals.coreui.screens.vitals

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.uservitals.R
import com.android.uservitals.coreui.BaseDaggerFragment
import com.android.uservitals.coreui.UiSignal
import com.android.uservitals.domain.AllVitals
import javax.inject.Inject

class UserVitalsFragment : BaseDaggerFragment(),OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserVitalsViewModel
    private lateinit var recycleView: RecyclerView
    private var adapter: VitalsAdapter? = null


    override fun getLayout(): Int {
        return R.layout.user_vitals
    }

    override fun onViewCreationCompleted(view: View) {
        activity?.setTitle(R.string.app_name)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UserVitalsViewModel::class.java)
        val viewManager = LinearLayoutManager(requireContext())
        recycleView = view.findViewById<RecyclerView>(R.id.recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager

        }
        viewModel.getUiState().observe(viewLifecycleOwner, Observer { uiSignal ->
            if (uiSignal is UiSignal.Success<*>) {
                val vitalsData = uiSignal.data as AllVitals
                adapter = VitalsAdapter(vitalsData.vitals,this)
                recycleView.adapter = adapter
            }

        })
        viewModel.fetchVitals()
    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return viewModel.getUiState()
    }

    override fun onItemClicked(type: String) {
        //navigate to details page
    }
}