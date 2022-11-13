package com.medwiz.medwiz.auth.signUp
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentAddDocInfoBinding
import com.medwiz.medwiz.databinding.WorkingTimeDialogBinding
import com.medwiz.medwiz.model.*
import com.medwiz.medwiz.patientsView.booking.doctorDetails.WorkingTimeAdapter
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddDocInfoFragment:Fragment(R.layout.fragment_add_doc_info) {
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
            val licencePath=binding.btUpload.text.toString()
            val workTimeList=getWorkTime()
            val reviewList=getReviewList()

            if(specialization.isNotEmpty()||experience.isNotEmpty()||about.isNotEmpty()||workTimeList.size>0||reviewList.size>0){
                val doctorInfo=DoctorInfo()
                 doctorInfo.experience=experience
                  doctorInfo.specialization=specialization
                 doctorInfo.licencePath=licencePath
                 doctorInfo.about=about
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