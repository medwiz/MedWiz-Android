package com.medwiz.medwiz.auth.signUp
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentAddDocInfoBinding
import com.medwiz.medwiz.databinding.WorkingTimeDialogBinding
import com.medwiz.medwiz.doctorsView.viewModels.DoctorViewModel
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.model.WorkTimings
import com.medwiz.medwiz.patientsView.booking.doctorDetails.SelectDateAdapter
import com.medwiz.medwiz.patientsView.booking.doctorDetails.SelectTimeAdapter
import com.medwiz.medwiz.patientsView.booking.doctorDetails.WorkingTimeAdapter
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.UtilConstants

import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class AddDocInfoFragment:Fragment(R.layout.fragment_add_doc_info) {
    private val viewModel: AuthViewModel by viewModels()
    private val doctorViewModel:DoctorViewModel by viewModels()
    private var workingTimeAdapter: WorkingTimeAdapter?=null
    lateinit var dialog: Dialog
    var password:String=""
    var confirmPassword:String=""
    var request:RegisterRequest= RegisterRequest()
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private lateinit var binding: FragmentAddDocInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddDocInfoBinding.bind(view)
        request= arguments?.getParcelable<RegisterRequest>(UtilConstants.request)!!
        binding.btSelectWorkingTime.setOnClickListener{
            openDialog(requireView())
        }

        binding.btNextStep.setOnClickListener {

            val specialization=binding.etSpecialization.text.toString()
            val experience=binding.etYearsOfExperience.text.toString()
            val about=binding.etAboutYou.text.toString()
            val workTimeList=getWorkTime()
            if(specialization.isNotEmpty()||experience.isNotEmpty()||about.isNotEmpty()){
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
            }
           // if(TextUtils.isEmpty(specialization))

            val bundle = Bundle()
            bundle.putParcelable(UtilConstants.request,request)
            findNavController().navigate(R.id.action_addDocInfoFragment_to_createPassword,bundle)
        }

    }

    private fun getWorkTime(): ArrayList<WorkTimings> {
        val list=ArrayList<WorkTimings>()
        val worTimeMonday=WorkTimings()
        worTimeMonday.day="Monday"
        worTimeMonday.time="8:00 am to 8:00 pm"
        list.add(worTimeMonday)

        val worTimeTuesday=WorkTimings()
        worTimeTuesday.day="Tuesday"
        worTimeTuesday.time="8:00 am to 8:00 pm"
        list.add(worTimeTuesday)

        val worTimeWednesday=WorkTimings()
        worTimeWednesday.day="Wednesday"
        worTimeWednesday.time="8:00 am to 8:00 pm"
        list.add(worTimeWednesday)

        val worTimeThursDay=WorkTimings()
        worTimeThursDay.day="Thursday"
        worTimeThursDay.time="8:00 am to 8:00 pm"
        list.add(worTimeThursDay)

        val worTimeFriday=WorkTimings()
        worTimeFriday.day="Friday"
        worTimeFriday.time="8:00 am to 8:00 pm"
        list.add(worTimeFriday)

        val worTimeSaturday=WorkTimings()
        worTimeSaturday.day="Saturday"
        worTimeSaturday.time="8:00 am to 8:00 pm"
        list.add(worTimeSaturday)

        val worTimeSunday=WorkTimings()
        worTimeSunday.day="Sunday"
        worTimeSunday.time="8:00 am to 8:00 pm"
        list.add(worTimeSunday)


        return list

    }

    fun openDialog(view: View) {
         dialog = Dialog(requireContext())
        val dialogAlertCommonBinding = WorkingTimeDialogBinding
            .inflate(LayoutInflater.from(context));
        dialog.setContentView(dialogAlertCommonBinding.root)
        val lis=ArrayList<String>()
        lis.add("Monday")
        lis.add("Tuesday")
        lis.add("Wednesday")
        lis.add("Thursday")
        lis.add("Friday")
        lis.add("Saturday")
        lis.add("Sunday")
        workingTimeAdapter = WorkingTimeAdapter(requireActivity(),lis)
        dialogAlertCommonBinding.rcvWorkingTime.adapter = workingTimeAdapter
        dialogAlertCommonBinding.rcvWorkingTime.layoutManager = LinearLayoutManager(requireActivity())
        dialog.show()
    }
    fun closeDialog(view: View) {
        dialog.dismiss()

    }
}