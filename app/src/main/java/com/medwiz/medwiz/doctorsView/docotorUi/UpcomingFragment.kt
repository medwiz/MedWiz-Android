package com.medwiz.medwiz.doctorsView.docotorUi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentUpcomingBinding
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionMainActivity
import com.medwiz.medwiz.doctorsView.docotorUi.home.PatientAdapter
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class UpcomingFragment:Fragment(R.layout.fragment_upcoming), HomeScreenListener {
    private lateinit var binding:FragmentUpcomingBinding
    private var adapter: PatientAdapter?=null
    private var userDetails: LoginResponse?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingBinding.bind(view)


//        adapter = PatientAdapter(requireActivity(),UtilConstants.TYPE_UPCOMING,lis,this)
//        binding.rcvPatient.adapter = adapter
//        binding.rcvPatient.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: DoctorResponse) {
        val intent = Intent (requireActivity(), PrescriptionMainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()


    }

}