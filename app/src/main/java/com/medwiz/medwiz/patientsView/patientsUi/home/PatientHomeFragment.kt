package com.medwiz.medwiz.patientsView.booking.patient.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPatientHomeBinding
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.patientsView.booking.HealthTypeAdapter
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PatientHomeFragment:Fragment(R.layout.fragment_patient_home), HomeScreenListener {
    private var adapter: DoctorsAdapter?=null
    private var healthTypeAdapter:HealthTypeAdapter?=null
    private lateinit var binding: FragmentPatientHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPatientHomeBinding.bind(view)

        val d= Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d1= Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d2= Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val lis= ArrayList<Doctors>()
        lis.add(d)
        lis.add(d1)
        lis.add(d2)

        adapter = DoctorsAdapter(requireActivity(),lis,this)
        binding.rcvNearByDoc.adapter = adapter
        binding.rcvNearByDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcvNearByDoc.smoothScrollBy(50,100)



        healthTypeAdapter = HealthTypeAdapter(requireActivity(),lis,this)
        binding.rcvHealthType.adapter = healthTypeAdapter
        binding.rcvHealthType.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
     //   binding.rcvHealthType.smoothScrollBy(100,100)



        binding.rcvAllDoc.adapter = adapter
        binding.rcvAllDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcvAllDoc.smoothScrollBy(100,100)

        binding.btNearByViewAll.setOnClickListener{
            val bundle=Bundle()
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_patientHomeFragment_to_viewAllDoctorsFragment,bundle)
        }
        binding.btTopViewAll.setOnClickListener{
            val bundle=Bundle()
            bundle.putBoolean(UtilConstants.nearbyDocs,false)
            findNavController().navigate(R.id.action_patientHomeFragment_to_viewAllDoctorsFragment,bundle)
        }
    }

    override fun onClickConsult(position: Int, doctor: Doctors) {
        val bundle=Bundle()
        bundle.putBoolean(UtilConstants.nearbyDocs,true)
        findNavController().navigate(R.id.action_patientHomeFragment_to_doctorDetails,bundle)
    }
}