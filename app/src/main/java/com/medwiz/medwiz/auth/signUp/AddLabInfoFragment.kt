package com.medwiz.medwiz.auth.signUp
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentAddDocInfoBinding
import com.medwiz.medwiz.databinding.FragmentAddLabInfoBinding
import com.medwiz.medwiz.doctorsView.viewModels.DoctorViewModel
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.util.MedWizConstants

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddLabInfoFragment:Fragment(R.layout.fragment_add_lab_info) {
    private val viewModel: AuthViewModel by viewModels()
    private val doctorViewModel:DoctorViewModel by viewModels()
    var password:String=""
    var confirmPassword:String=""
    var request:RegisterRequest= RegisterRequest()
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private lateinit var binding: FragmentAddLabInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddLabInfoBinding.bind(view)


    }
}