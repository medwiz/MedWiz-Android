package com.medwiz.medwiz.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.LabView.ui.LabActivity
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SelectAccountFragmentBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.patientsView.booking.patient.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizConstants

class SelectAccountFragment:Fragment(R.layout.select_account_fragment) {
    private lateinit var binding:SelectAccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SelectAccountFragmentBinding.bind(view)

        binding.rlDoctor.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_DOCTOR)
//            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)

            val intent = Intent (requireActivity(), DoctorsActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }

        binding.rlPatient.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_PATIENT)
//            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)
            val intent = Intent (requireActivity(), PatientMainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
        binding.rlLab.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_LAB)
//            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)

            val intent = Intent (requireActivity(), LabActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }
}