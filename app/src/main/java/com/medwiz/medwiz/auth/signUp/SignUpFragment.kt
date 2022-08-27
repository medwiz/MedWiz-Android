package com.medwiz.medwiz.auth.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentSignUpBinding
import com.medwiz.medwiz.util.MedWizConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment:Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private var accountType:String= MedWizConstants.Auth.ACCOUNT_DOCTOR
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
        when(accountType){
            MedWizConstants.Auth.ACCOUNT_DOCTOR->{

                binding.imgProfile.setImageResource(R.drawable.ic_doctor_blue)
            }
            MedWizConstants.Auth.ACCOUNT_PATIENT->{

                binding.imgProfile.setImageResource(R.drawable.ic_patient_blue)
            }
            MedWizConstants.Auth.ACCOUNT_LAB->{
                binding.imgProfile.setImageResource(R.drawable.ic_lab_blue)
            }
        }
        binding.imgBackCreateAccount.setOnClickListener{

            findNavController().navigateUp()


        }

        binding.btNextStep.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            findNavController().navigate(R.id.action_signUpFragment_to_createPassword,bundle)
        }
    }
}