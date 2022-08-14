package com.medwiz.medwiz.ui.patient.doctorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorDetailsBinding

class DoctorDetails : Fragment(R.layout.fragment_doctor_details){
    private lateinit var binding: FragmentDoctorDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorDetailsBinding.bind(view)
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

}