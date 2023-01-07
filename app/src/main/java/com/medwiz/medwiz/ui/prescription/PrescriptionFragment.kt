package com.medwiz.medwiz.ui.prescription

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPrescriptionBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PrescriptionFragment:Fragment(R.layout.fragment_prescription), HomeScreenListener {
    private lateinit var binding: FragmentPrescriptionBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrescriptionBinding.bind(view)

    }


    override fun onClickConsult(position: Int, doctor: DoctorResponse) {


    }
}