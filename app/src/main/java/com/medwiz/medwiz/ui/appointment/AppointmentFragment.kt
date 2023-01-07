package com.medwiz.medwiz.ui.appointment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocAppointmentBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AppointmentFragment:Fragment(R.layout.fragment_doc_appointment), HomeScreenListener {
    private lateinit var binding: FragmentDocAppointmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocAppointmentBinding.bind(view)

    }


    override fun onClickConsult(position: Int, doctor: DoctorResponse) {


    }
}