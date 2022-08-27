package com.medwiz.medwiz.auth.signUp
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentCreatePasswordBinding
import com.medwiz.medwiz.main.mainViewModels.CreateAccountViewModel
import com.medwiz.medwiz.util.MedWizConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreatePassword:Fragment(R.layout.fragment_create_password) {
    private val viewModel: CreateAccountViewModel by viewModels()
    var password:String=""
    var confirmPassword:String=""
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private lateinit var binding: FragmentCreatePasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePasswordBinding.bind(view)
        accountType = arguments?.getString(MedWizConstants.Auth.ACCOUNT_TYPE)!!
      // val registerUserData= arguments?.getParcelable<EmergencyContact>("contact")

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
            enterOtpDialog()
        }


        binding.showRepeatPassBtn.setOnClickListener {
            showHidePass( binding.showRepeatPassBtn,binding.etRepeatPassword)

        }
        binding.showPassBtn.setOnClickListener {
            showHidePass( binding.showPassBtn,binding.etPassword)

        }

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



    private fun callLoginApi(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_error_layout)
        val body = dialog.findViewById(R.id.tvPopup) as TextView
      //  body.text = requireActivity().getText(R.string.ACCOUNT_NOT_FULLY_SETUP)
        val yesBtn = dialog.findViewById(R.id.btOk) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            //findNavController().navigate(R.id.action_createPassword_to_withAccountScreen)
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
}