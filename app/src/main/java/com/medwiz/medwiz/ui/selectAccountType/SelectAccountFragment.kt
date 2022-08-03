package com.medwiz.medwiz.ui.selectAccountType

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SelectAccountFragmentBinding
import com.medwiz.medwiz.util.MedWizConstants

class SelectAccountFragment:Fragment(R.layout.select_account_fragment) {
    private lateinit var binding:SelectAccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SelectAccountFragmentBinding.bind(view)

        binding.rlDoctor.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_DOCTOR)
            findNavController().navigate(R.id.action_selectAccountFragment_to_accountSelection,bundle)
        }

        binding.rlPatient.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_PATIENT)
            findNavController().navigate(R.id.action_selectAccountFragment_to_accountSelection,bundle)
        }
        binding.rlLab.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_LAB)
            findNavController().navigate(R.id.action_selectAccountFragment_to_accountSelection,bundle)
        }
    }
}