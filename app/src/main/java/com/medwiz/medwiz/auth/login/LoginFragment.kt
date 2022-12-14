package com.medwiz.medwiz.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentLoginBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.shopView.ShopActivity
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private var accountType: String = MedWizConstants.Auth.ACCOUNT_DOCTOR
    private val viewModel: AuthViewModel by viewModels()
    private var isComingFromCreatePassword: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
        isComingFromCreatePassword =
            requireArguments().getBoolean(MedWizConstants.AppValue.SCREEN_NAME, false)
        binding.imgBackAddInfo.setOnClickListener {

            findNavController().navigateUp()


        }
        binding.showLoginPassBtn.setOnClickListener {
            showHidePass(binding.showLoginPassBtn, binding.etPassword)
        }
        binding.btLogin.setOnClickListener {


            val userPhoneNumber = binding.etUserPhoneNumber.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (accountType == MedWizConstants.Auth.ACCOUNT_SHOP) {
                viewModel.loginShop(userPhoneNumber, password)
            } else {
                viewModel.login(userPhoneNumber, password)
            }

        }
        binding.liSignUp.setOnClickListener {

            if (accountType == MedWizConstants.Auth.ACCOUNT_SHOP) {
                findNavController().navigate(R.id.action_loginFragment_to_addLabInfoFragment)
            } else {
                val bundle = Bundle()
                bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE, accountType)
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment, bundle)
            }
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

        viewModel.loginShop.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success -> {
                    if (accountType == it.data!!.userType) {
                        MedWizUtils.storeValueInPreference(
                            requireContext(),
                            UtilConstants.accessToken,
                            "Bearer " + it.data.token,
                            true
                        )
                        MedWizUtils.storeValueInPreference(
                            requireContext(),
                            UtilConstants.userId,
                            it.data.id.toString(),
                            true
                        )
                        MedWizUtils.storeValueInPreference(
                            requireContext(),
                            UtilConstants.email,
                            it.data.email,
                            true
                        )
                        MedWizUtils.storeValueInPreference(
                            requireContext(),
                            UtilConstants.userType,
                            it.data.userType,
                            true
                        )
                        val intent = Intent(requireActivity(), ShopActivity::class.java)
                        requireActivity().startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "You have chosen wrong account type",
                            Toast.LENGTH_SHORT
                        ).show()
                        goBack()
                    }

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
        val intent = Intent(requireActivity(), PatientMainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()


    }

}