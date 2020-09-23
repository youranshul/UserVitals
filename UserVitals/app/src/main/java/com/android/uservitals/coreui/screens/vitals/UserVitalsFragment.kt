package com.android.uservitals.coreui.screens.vitals

import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.uservitals.R
import com.android.uservitals.coreui.BaseDaggerFragment
import com.android.uservitals.coreui.UiSignal
import com.android.uservitals.domain.vitals.AllVitals
import com.android.uservitals.domain.vitals.UserVitalsViewModel
import javax.inject.Inject

class UserVitalsFragment : BaseDaggerFragment(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserVitalsViewModel
    private lateinit var recycleView: RecyclerView
    private var adapter: VitalsAdapter? = null

    private lateinit var userName: TextView
    private lateinit var userDob: TextView
    private lateinit var userCity: TextView


    override fun getLayout(): Int {
        return R.layout.user_vitals
    }

    override fun onViewCreationCompleted(view: View) {
        activity?.setTitle(R.string.app_name)
        userName = view.findViewById(R.id.userName)
        userDob = view.findViewById(R.id.dob)
        userCity = view.findViewById(R.id.place)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UserVitalsViewModel::class.java)
        val viewManager = LinearLayoutManager(requireContext())
        recycleView = view.findViewById<RecyclerView>(R.id.recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager

        }
        viewModel.getUiState().observe(viewLifecycleOwner, Observer { uiSignal ->
            if (uiSignal is UiSignal.Success<*>) {
                val allVitals = uiSignal.data as AllVitals
                updateHeaderView(allVitals.name, allVitals.dob, allVitals.city)
                adapter = VitalsAdapter(allVitals.vitals, this)
                recycleView.adapter = adapter
            }

        })
        viewModel.fetchVitals()
    }

    private fun updateHeaderView(name: String, dob: String, city: String) {
        userName.text = name
        userDob.text = dob
        userCity.text = city
    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return viewModel.getUiState()
    }

    override fun onItemClicked(type: String) {
        findNavController().navigate(
            R.id.action_VitalsFragment_to_DetailsFragment, bundleOf(
                SpecificVitalFragment.TITLE to type
            )
        )
    }
}