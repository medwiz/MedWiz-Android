package com.medwiz.medwiz.ui.patient.doctorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorDetailsBinding
import com.medwiz.medwiz.models.Doctors
import com.medwiz.medwiz.ui.patient.home.DoctorsAdapter
import com.medwiz.medwiz.ui.patient.home.HomeScreenListener
import java.util.*

class DoctorDetails : Fragment(R.layout.fragment_doctor_details), HomeScreenListener {
    private lateinit var binding: FragmentDoctorDetailsBinding
    private var doctorAdapter: DoctorsAdapter?=null
    private var workingTimeAdapter:WorkingTimeAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorDetailsBinding.bind(view)
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }

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

        workingTimeAdapter = WorkingTimeAdapter(requireActivity(),lis)
        binding.rcvWorkingTime.adapter = workingTimeAdapter
        binding.rcvWorkingTime.layoutManager = LinearLayoutManager(requireActivity())


        doctorAdapter = DoctorsAdapter(requireActivity(),lis,this)
        binding.rcvReview.adapter = doctorAdapter
        binding.rcvReview.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: Doctors) {

    }

}