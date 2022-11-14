package com.medwiz.medwiz.doctorsView.docotorUi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentCompletedBinding
import com.medwiz.medwiz.doctorsView.docotorUi.home.PatientAdapter
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompletedFragment:Fragment(R.layout.fragment_completed), HomeScreenListener {
    private lateinit var binding:FragmentCompletedBinding
    private var adapter: PatientAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCompletedBinding.bind(view)


//        adapter = PatientAdapter(requireActivity(), UtilConstants.TYPE_COMPLETED,lis,this)
//        binding.rcvPatient.adapter = adapter
//        binding.rcvPatient.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: DoctorResponse) {

    }
}