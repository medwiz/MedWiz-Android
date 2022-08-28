package com.medwiz.medwiz.doctorsView.docotorUi.appointment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocAppointmentBinding
import com.medwiz.medwiz.databinding.FragmentPatientBookingBinding

class DocAppointmentFragment:Fragment(R.layout.fragment_doc_appointment) {
    private lateinit var binding: FragmentDocAppointmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocAppointmentBinding.bind(view)

    }
}