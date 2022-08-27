package com.medwiz.medwiz.doctorsView.docotorUi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentCanceledBinding
import com.medwiz.medwiz.databinding.FragmentCompletedBinding
import com.medwiz.medwiz.doctorsView.docotorUi.home.PatientAdapter
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
        val d= Doctors("1","Patient One",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d1= Doctors("1","Patient Two",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d2= Doctors("1","Patient Three",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d3= Doctors("3","Patient Four",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val lis= ArrayList<Doctors>()
        lis.add(d)
        lis.add(d1)
        lis.add(d2)
        lis.add(d3)


        adapter = PatientAdapter(requireActivity(), UtilConstants.TYPE_CANCELLED,lis,this)
        binding.rcvPatient.adapter = adapter
        binding.rcvPatient.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: Doctors) {

    }
}