package com.medwiz.medwiz.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.LabView.ui.LabActivity
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentLoginBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.patientsView.booking.patient.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :Fragment(R.layout.fragment_login){
    private lateinit var binding: FragmentLoginBinding
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private var isComingFromCreatePassword:Boolean=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
        isComingFromCreatePassword= requireArguments().getBoolean(MedWizConstants.AppValue.SCREEN_NAME,false)

//        if(isComingFromCreatePassword){
//             MedWizUtils.showErrorPopup(requireActivity(),"Your Profile has been created, Please login now.")
//        }
//        when(accountType){
//            MedWizConstants.Auth.ACCOUNT_DOCTOR->{
////                binding.tvLogin.text=getString(R.string.as_a_doc)
////                binding.imgProfile.setImageResource(R.drawable.ic_doctor_blue)
//                val intent = Intent (requireActivity(), DoctorsActivity::class.java)
//                requireActivity().startActivity(intent)
//                requireActivity().finish()
//            }
//            MedWizConstants.Auth.ACCOUNT_PATIENT->{
////                binding.tvLogin.text=getString(R.string.as_a_patient)
////                binding.imgProfile.setImageResource(R.drawable.ic_patient_blue)
//                val intent = Intent (requireActivity(), PatientMainActivity::class.java)
//                requireActivity().startActivity(intent)
//                requireActivity().finish()
//            }
//            MedWizConstants.Auth.ACCOUNT_LAB->{
////                binding.tvLogin.text=getString(R.string.as_a_lab)
////                binding.imgProfile.setImageResource(R.drawable.ic_lab_blue)
//                val intent = Intent (requireActivity(), LabActivity::class.java)
//                requireActivity().startActivity(intent)
//                requireActivity().finish()
//            }
//        }
//        binding.imgBack.setOnClickListener{
//
//            findNavController().navigateUp()
//
//        }
        binding.btContinue.setOnClickListener {

//            if(binding.etPhoneNumber.text.toString().isEmpty()){
//                return@setOnClickListener
//            }
             val bundle=Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            bundle.putString(MedWizConstants.AppValue.PHONE_NUMBER,binding.etPhoneNumber.text.toString())
               findNavController().navigate(R.id.action_loginFragment_to_verificationFragment,bundle)
        }

        binding.liSignUp.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment,bundle)
        }

//        binding.btContinue.setOnClickListener{
//            when(accountType){
//                MedWizConstants.Auth.ACCOUNT_DOCTOR->{
//                    val intent = Intent (requireActivity(), DoctorsActivity::class.java)
//                    requireActivity().startActivity(intent)
//                    requireActivity().finish()
//                }
//                MedWizConstants.Auth.ACCOUNT_PATIENT->{
//                    val intent = Intent (requireActivity(), PatientMainActivity::class.java)
//                    requireActivity().startActivity(intent)
//                    requireActivity().finish()
//                }
//                MedWizConstants.Auth.ACCOUNT_LAB->{
//                    val intent = Intent (requireActivity(), LabActivity::class.java)
//                    requireActivity().startActivity(intent)
//                    requireActivity().finish()
//                }
//            }
//        }
    }
}