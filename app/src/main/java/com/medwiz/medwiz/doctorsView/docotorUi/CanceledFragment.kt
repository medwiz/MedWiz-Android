package com.medwiz.medwiz.doctorsView.docotorUi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentCanceledBinding
import com.medwiz.medwiz.databinding.FragmentCompletedBinding
import com.medwiz.medwiz.doctorsView.docotorUi.home.PatientAdapter
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class CanceledFragment:Fragment(R.layout.fragment_canceled), HomeScreenListener {
    private lateinit var binding:FragmentCanceledBinding
    private var adapter: PatientAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCanceledBinding.bind(view)



//        adapter = PatientAdapter(requireActivity(), UtilConstants.TYPE_CANCELLED,lis,this)
//        binding.rcvPatient.adapter = adapter
//        binding.rcvPatient.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: DoctorResponse) {

    }
}