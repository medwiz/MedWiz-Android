package com.medwiz.medwiz.auth.signUp
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PackageManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentAddDocInfoBinding
import com.medwiz.medwiz.databinding.WorkingTimeDialogBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.model.*
import com.medwiz.medwiz.patientsView.booking.doctorDetails.WorkingTimeAdapter
import com.medwiz.medwiz.util.*
import com.medwiz.medwiz.viewmodels.FileViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileDescriptor


@AndroidEntryPoint
class AddDocInfoFragment:Fragment(R.layout.fragment_add_doc_info) {
    private var workingTimeAdapter: WorkingTimeAdapter?=null
    lateinit var dialog: Dialog
    var password:String=""
    var confirmPassword:String=""
    var downloadUrl:String=""
    private val viewModel: FileViewModel by viewModels()
    var request:RegisterRequest= RegisterRequest()
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_DOCTOR
    private lateinit var binding: FragmentAddDocInfoBinding
    private val getFile=registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        val file: File = FileUtil.from(requireContext(), it)
        binding.btUpload.text = file.name
        val filePart=FileUtil.prepareFilePart("file",file,it,requireContext())
        viewModel.uploadFile(filePart)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddDocInfoBinding.bind(view)
        request= arguments?.getParcelable<RegisterRequest>(UtilConstants.request)!!
        binding.btSelectWorkingTime.setOnClickListener{
            openDialog(requireView())
        }
        binding.btUpload.setOnClickListener {
            getFile.launch("application/pdf")
        }

       viewModel.uploadFile.observe(viewLifecycleOwner, Observer {
           when(it){
               is Resource.Loading->{
                   (activity as MainActivity).showLoading()
               }

               is Resource.Success->{
                   (activity as MainActivity).hideLoading()
                    downloadUrl=it.data!!.downloadUrl

               }
               is Resource.Error->{
                   (activity as MainActivity).hideLoading()
                   MedWizUtils.showErrorPopup(
                       requireActivity(),
                       it.message.toString())
               }
           }
       })

        binding.btNextStep.setOnClickListener {

            val specialization=binding.etSpecialization.text.toString()
            val experience=binding.etYearsOfExperience.text.toString()
            val about=binding.etAboutYou.text.toString()
            val licencePath=binding.btUpload.text.toString()
            val fees=binding.etFees.text.toString()
            val workTimeList=getWorkTime()
            val reviewList=getReviewList()

            if(specialization.isNotEmpty()&&experience.isNotEmpty()&&about.isNotEmpty()&&workTimeList.size>0&&reviewList.size>0&&fees.isNotEmpty()&&downloadUrl.isNotEmpty()){
                val doctorInfo=DoctorInfo()
                doctorInfo.experience=experience
                doctorInfo.specialization=specialization
                doctorInfo.licencePath=downloadUrl
                doctorInfo.about=about
                doctorInfo.fees=fees.toInt()
                val bundle = Bundle()
                bundle.putParcelableArrayList(UtilConstants.reviewList,reviewList)
                bundle.putParcelable(UtilConstants.doctorInfo,doctorInfo)
                bundle.putParcelableArrayList(UtilConstants.workingTimeList,workTimeList)
                bundle.putParcelable(UtilConstants.request,request)
                findNavController().navigate(R.id.action_addDocInfoFragment_to_createPassword,bundle)
            }
           // if(TextUtils.isEmpty(specialization))


        }

    }
    private fun getReviewList(): ArrayList<Review> {
        val list=java.util.ArrayList<Review>()
        val review=Review()
        review.reviewerName=""
        review.comments=""
        review.rating=0
         list.add(review)
        return list
    }
    private fun getWorkTime(): ArrayList<WorkTimings> {
        val list=ArrayList<WorkTimings>()
        val worTimeMonday=WorkTimings()
        worTimeMonday.day="Monday"
        worTimeMonday.startTime="8:00 am"
        worTimeMonday.endTime="8:00 pm"
        list.add(worTimeMonday)

        val worTimeTuesday=WorkTimings()
        worTimeTuesday.day="Tuesday"
        worTimeTuesday.startTime="8:00 am"
        worTimeTuesday.endTime="8:00 pm"
        list.add(worTimeTuesday)

        val worTimeWednesday=WorkTimings()
        worTimeWednesday.day="Wednesday"
        worTimeWednesday.startTime="8:00 am"
        worTimeWednesday.endTime="8:00 pm"
        list.add(worTimeWednesday)

        val worTimeThursDay=WorkTimings()
        worTimeThursDay.day="Thursday"
        worTimeThursDay.startTime="8:00 am"
        worTimeThursDay.endTime="8:00 pm"
        list.add(worTimeThursDay)

        val worTimeFriday=WorkTimings()
        worTimeFriday.day="Friday"
        worTimeFriday.startTime="8:00 am"
        worTimeFriday.endTime="8:00 pm"
        list.add(worTimeFriday)

        val worTimeSaturday=WorkTimings()
        worTimeSaturday.day="Saturday"
        worTimeSaturday.startTime="8:00 am"
        worTimeSaturday.endTime="8:00 pm"
        list.add(worTimeSaturday)

        val worTimeSunday=WorkTimings()
        worTimeSunday.day="Sunday"
        worTimeSunday.startTime="8:00 am"
        worTimeSunday.endTime="8:00 pm"
        list.add(worTimeSunday)


        return list

    }

    private fun openDialog(view: View) {
         dialog = Dialog(requireContext())
        val dialogAlertCommonBinding = WorkingTimeDialogBinding
            .inflate(LayoutInflater.from(context));
        dialog.setContentView(dialogAlertCommonBinding.root)
        workingTimeAdapter = WorkingTimeAdapter(requireActivity())
        dialogAlertCommonBinding.rcvWorkingTime.adapter = workingTimeAdapter
        dialogAlertCommonBinding.rcvWorkingTime.layoutManager = LinearLayoutManager(requireActivity())
        workingTimeAdapter!!.setData(getWorkTime())
        dialog.show()
    }
    fun closeDialog(view: View) {
        dialog.dismiss()

    }





}