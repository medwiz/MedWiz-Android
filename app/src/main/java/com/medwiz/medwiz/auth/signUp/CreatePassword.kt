package com.medwiz.medwiz.auth.signUp
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentCreatePasswordBinding
import com.medwiz.medwiz.doctorsView.viewModels.DoctorViewModel
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.main.mainViewModels.CreateAccountViewModel
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreatePassword:Fragment(R.layout.fragment_create_password) {
    private val viewModel: AuthViewModel by viewModels()
    private val doctorViewModel:DoctorViewModel by viewModels()

    var password:String=""
    var confirmPassword:String=""
    var request:RegisterRequest= RegisterRequest()
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private lateinit var binding: FragmentCreatePasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePasswordBinding.bind(view)
        request= arguments?.getParcelable<RegisterRequest>(UtilConstants.request)!!
        accountType=request.userType
        binding.imgBackCreatePassword.setOnClickListener {

            findNavController().navigateUp()
        }


        when(accountType){
            MedWizConstants.Auth.ACCOUNT_DOCTOR->{
                binding.tvQualification.visibility=View.VISIBLE
                binding.etExperience.visibility=View.VISIBLE
            }
            MedWizConstants.Auth.ACCOUNT_PATIENT->{
                binding.tvQualification.visibility=View.GONE
                binding.etExperience.visibility=View.GONE
            }
            MedWizConstants.Auth.ACCOUNT_LAB->{
                binding.tvQualification.visibility=View.GONE
                binding.etExperience.visibility=View.GONE
            }
        }

        binding.btCreateAccount.setOnClickListener {
            val password=binding.etPassword.text.toString()
            val repeatPassword=binding.etRepeatPassword.text.toString()
            if(password.isNotEmpty()){
                    request.password=password
                   viewModel.signUp(request)
            }


        }


        binding.showRepeatPassBtn.setOnClickListener {
            showHideRepeatPass( binding.showRepeatPassBtn,binding.etRepeatPassword)

        }
        binding.showPassBtn.setOnClickListener {
            showHidePass( binding.showPassBtn,binding.etPassword)

        }

        viewModel.register.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{

                    goToLoginScreen(it.data!!.message)

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

    }

    private fun updateDocsDb() {

    }


    private fun enterOtpDialog(){
        val view = View.inflate(requireActivity(), R.layout.enter_otp_popup, null)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)

        val updateDialog = builder.create()
        updateDialog.show()
        updateDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        updateDialog.setCancelable(false)
        val etOtp=view.findViewById<EditText>(R.id.etOtp)
        val btDone= view.findViewById<Button>(R.id.btDone)
        val btCancel= view.findViewById<Button>(R.id.btCancel)
        btDone.setOnClickListener {
            updateDialog.dismiss()
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,accountType)
            bundle.putBoolean(MedWizConstants.AppValue.SCREEN_NAME,true)
            findNavController().navigate(R.id.action_createPassword_to_loginFragment,bundle)


        }
        btCancel.setOnClickListener {

            updateDialog.dismiss()

        }

    }



    private fun goToLoginScreen(message:String){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_error_layout)
        val body = dialog.findViewById(R.id.tvPopup) as TextView
        body.text = message
        val yesBtn = dialog.findViewById(R.id.btOk) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_createPassword_to_selectAccountFragment)
        }
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

    }

    private fun showHidePass(view: View,editText:EditText) {
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

    private fun showHideRepeatPass(view: View,editText:EditText) {
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
}