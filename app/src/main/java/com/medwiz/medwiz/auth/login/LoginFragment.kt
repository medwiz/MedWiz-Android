package com.medwiz.medwiz.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.LabView.ui.LabActivity
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentLoginBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.patientsView.booking.patient.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :Fragment(R.layout.fragment_login){
    private lateinit var binding: FragmentLoginBinding
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private val viewModel: AuthViewModel by viewModels()
    private var isComingFromCreatePassword:Boolean=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
        isComingFromCreatePassword= requireArguments().getBoolean(MedWizConstants.AppValue.SCREEN_NAME,false)

        binding.btContinue.setOnClickListener {

            val username =  binding.etEmail.text.toString().trim()
            val password =  binding.etPassword.text.toString().trim()
            viewModel.login(username,password)

        }

        binding.liSignUp.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment,bundle)
        }

        viewModel.login.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{

                    goToNextScreen()

                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
//                    if(it.message==UtilConstants.unauthorized){
//                        MedWizUtils.showErrorPopup(
//                            requireActivity(),
//                            requireActivity().getString(R.string.INVALID_USER_CREDENTIALS))
//
//                    }
//                    else{
//                        MedWizUtils.showErrorPopup(
//                            requireActivity(),
//                            requireActivity().getString(R.string.something_went_wrong))
//                    }
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

        binding.etEmail.setText("s@gmail.com")
        binding.etPassword.setText("s12345")
    }

    private fun goToNextScreen() {
        val bundle=Bundle()
        bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
        bundle.putString(MedWizConstants.AppValue.PHONE_NUMBER,binding.etEmail.text.toString())
        findNavController().navigate(R.id.action_loginFragment_to_verificationFragment,bundle)
    }
}