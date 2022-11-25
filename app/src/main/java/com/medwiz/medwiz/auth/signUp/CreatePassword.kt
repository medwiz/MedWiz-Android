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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentCreatePasswordBinding
import com.medwiz.medwiz.viewmodels.DoctorViewModel
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.model.DoctorInfo
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.model.WorkTimings
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject


@AndroidEntryPoint
class CreatePassword:Fragment(R.layout.fragment_create_password) {
    private val viewModel: AuthViewModel by viewModels()
    private val doctorViewModel: DoctorViewModel by viewModels()
    private var doctorInfOJsonObject=JSONObject()
    private var password:String=""
    private var confirmPassword:String=""
    private var registeredEmail:String=""
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
               val i= getJsonObject();
                val k=0
            }
            MedWizConstants.Auth.ACCOUNT_PATIENT->{

            }
            MedWizConstants.Auth.ACCOUNT_LAB->{

            }
        }

        binding.btCreateAccount.setOnClickListener {
            val password=binding.etPassword.text.toString()
            val repeatPassword=binding.etRepeatPassword.text.toString()

            if(password==repeatPassword){
                MedWizUtils.showErrorPopup(requireContext(),"Password not matched!!")
                return@setOnClickListener
            }

            if(password.isNotEmpty()){
                    request.password=password
                    this.registeredEmail=request.email
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
                    (activity as MainActivity).hideLoading()
                    if(it.data!!.success){
                        if(accountType== MedWizConstants.Auth.ACCOUNT_DOCTOR){
                        registerDoctor()
                        }else{
                          goToLoginScreen(it.data.message)
                         }
                    }


                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

        doctorViewModel.registerDoctor.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    if(it.data!!.success){
                        goToLoginScreen(it.data.message)
                    }


                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
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

    private fun getJsonObject():JsonObject{
        val doctorInfo=arguments?.getParcelable<DoctorInfo>(UtilConstants.doctorInfo)!!
        val workTimeList=arguments?.getParcelableArrayList<WorkTimings>(UtilConstants.workingTimeList)!!
        val reviewList=arguments?.getParcelableArrayList<Review>(UtilConstants.reviewList)!!
        val requestObj=JsonObject()
        requestObj.addProperty(UtilConstants.firstname, request.firstname)
        requestObj.addProperty(UtilConstants.lastname, request.lastname)
        requestObj.addProperty(UtilConstants.email, request.email)
        requestObj.addProperty(UtilConstants.mobile, request.mobile)
        requestObj.addProperty(UtilConstants.pinCode, request.pinCode)
        requestObj.addProperty(UtilConstants.age, request.age)
        requestObj.addProperty(UtilConstants.userType, request.userType)
        requestObj.addProperty(UtilConstants.credit, request.credit)
        requestObj.addProperty(UtilConstants.address,request.pinCode)
        requestObj.addProperty(UtilConstants.experience,doctorInfo.experience)
        requestObj.addProperty(UtilConstants.licencePath,doctorInfo.licencePath)
        requestObj.addProperty(UtilConstants.fees,doctorInfo.fees)
        requestObj.addProperty(UtilConstants.specialization,doctorInfo.specialization)
        requestObj.addProperty(UtilConstants.about,doctorInfo.about)
        requestObj.addProperty(UtilConstants.isActivated,request.isActivated)

        val workTimeArray= JsonArray()
        val reviewArray= JsonArray()
        for (i in 0 until workTimeList.size) {
            val workTimeObj=JsonObject()
            workTimeObj.addProperty("day",workTimeList[i].day)
            workTimeObj.addProperty("startTime",workTimeList[i].startTime)
            workTimeObj.addProperty("endTime",workTimeList[i].endTime)
            workTimeArray.add(workTimeObj)
        }
        for (i in 0 until reviewList.size) {
            val reviewObj=JsonObject()
            reviewObj.addProperty("reviewerName","MedWiz")
            reviewObj.addProperty("comments","")
            reviewObj.addProperty("rating",5)
            reviewObj.addProperty("docEmail",request.email)
            reviewObj.addProperty("avatar","")
            reviewObj.addProperty("heading","")
            requestObj.addProperty("addedDate","")
            reviewArray.add(reviewObj)
        }
        requestObj.add("workingTimes",workTimeArray)
        requestObj.add("reviews",reviewArray)

        return requestObj
    }

    private fun registerDoctor(){
        doctorViewModel.updateDoctor(getJsonObject())
    }
}