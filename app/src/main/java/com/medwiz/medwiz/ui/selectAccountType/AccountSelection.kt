package com.medwiz.medwiz.ui.selectAccountType

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentAccountSelectionBinding
import com.medwiz.medwiz.util.MedWizConstants

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountSelection:Fragment(R.layout.fragment_account_selection) {
    private lateinit var binding: FragmentAccountSelectionBinding
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountSelectionBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!

        when(accountType){
            MedWizConstants.Auth.ACCOUNT_DOCTOR->{
                binding.imageView.setImageResource(R.drawable.ic_doctor_blue)
            }
            MedWizConstants.Auth.ACCOUNT_PATIENT->{
                binding.imageView.setImageResource(R.drawable.ic_patient_blue)
            }
            MedWizConstants.Auth.ACCOUNT_LAB->{
                binding.imageView.setImageResource(R.drawable.ic_lab_blue)
            }
        }
        binding.imgBack.setOnClickListener{

            findNavController().navigateUp()


        }

        binding.tvLoginWithYour.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
                findNavController().navigate(R.id.action_accountSelection_to_loginFragment,bundle)


        }
        binding.tvNewAccount.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            findNavController().navigate(R.id.action_accountSelection_to_signUpFragment,bundle)


        }

    }






}