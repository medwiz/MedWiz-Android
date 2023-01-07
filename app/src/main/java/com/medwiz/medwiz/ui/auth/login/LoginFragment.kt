package com.medwiz.medwiz.ui.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentLoginBinding
import com.medwiz.medwiz.ui.main.MainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.imgBackAddInfo.setOnClickListener {

            findNavController().navigateUp()
        }
        checkAccessToken()
        binding.showLoginPassBtn.setOnClickListener {
            showHidePass(binding.showLoginPassBtn, binding.etPassword)
        }
        binding.btLogin.setOnClickListener {
            val userPhoneNumber = binding.etUserPhoneNumber.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

                viewModel.login(userPhoneNumber, password)


        }
        binding.liSignUp.setOnClickListener {

        }

        viewModel.login.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success -> {

                    goToNextScreen(it.data!!)


                }
                is Resource.Error -> {
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
                        it.message.toString()
                    )
                }
            }
        })


        binding.etUserPhoneNumber.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length == 10) {
                    binding.imgTick.setImageResource(R.drawable.ic_tick_green)
                } else {
                    binding.imgTick.setImageResource(R.drawable.ic_tick)
                }
            }
        })


//        binding.etEmail.setText("s@gmail.com")
//        binding.etPassword.setText("s12345")
    }

    private fun checkAccessToken() {
        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        if(token.isNotEmpty()){
           goToHomeScreen()
        }
    }

    private fun goBack() {
        requireActivity().finish()
    }

    private fun showHidePass(view: View, editText: EditText) {
        if (editText.transformationMethod
                .equals(PasswordTransformationMethod.getInstance())
        ) {
            (view as ImageView).setImageResource(R.drawable.ic_show_password)
            //Show Password
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            (view as ImageView).setImageResource(R.drawable.ic_hide_password)
            //Hide Password
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }

    }

    private fun goToNextScreen(it: LoginResponse) {
        MedWizUtils.storeValueInPreference(
            requireContext(),
            UtilConstants.accessToken,
            "Bearer " + it.token,
            true
        )
        MedWizUtils.storeValueInPreference(
            requireContext(),
            UtilConstants.userId,
             it.userId.toString(),
            true
        )

        goToHomeScreen()

    }

    private fun goToHomeScreen(){
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

}