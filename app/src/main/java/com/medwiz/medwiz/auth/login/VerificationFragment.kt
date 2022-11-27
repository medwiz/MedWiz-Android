package com.medwiz.medwiz.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentVerificationBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.LabView.ui.LabActivity
import com.medwiz.medwiz.patientsView.patientsUi.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationFragment:Fragment(R.layout.fragment_verification) {
    private lateinit var binding: FragmentVerificationBinding
    private var phoneNumber=""
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_PATIENT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVerificationBinding.bind(view)
        phoneNumber=arguments?.getString(MedWizConstants.AppValue.PHONE_NUMBER)!!
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
        binding.tvVerificationNumber.text=phoneNumber
        binding.imgBack.setOnClickListener{

            findNavController().navigateUp()

        }

        binding.btVerify.setOnClickListener{
            when(accountType){
                MedWizConstants.Auth.ACCOUNT_DOCTOR->{
                    val intent = Intent (requireActivity(), DoctorsActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                }
                MedWizConstants.Auth.ACCOUNT_PATIENT->{
                    val intent = Intent (requireActivity(), PatientMainActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                }
                MedWizConstants.Auth.ACCOUNT_SHOP->{
                    val intent = Intent (requireActivity(), LabActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                }
            }
       }
    }
}