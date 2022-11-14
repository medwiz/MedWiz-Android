package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorDetailsBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientsUi.doctorDetails.ReviewAdapter
import com.medwiz.medwiz.patientsView.patientsUi.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DoctorDetails : Fragment(R.layout.fragment_doctor_details) {
    private lateinit var binding: FragmentDoctorDetailsBinding
    private var reviewAdapter: ReviewAdapter?=null
    private val reviewViewModel: ReviewViewModel by viewModels()
    private var workingTimeAdapter:WorkingTimeAdapter?=null
    private var selectedDoctor:DoctorResponse?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorDetailsBinding.bind(view)
        selectedDoctor=arguments?.getParcelable<DoctorResponse>(UtilConstants.doctor)!!
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }

         createAdapter()
         val token:String=MedWizUtils.storeValueInPreference(requireContext(),UtilConstants.accessToken,"",false)
         reviewViewModel.getAllReviewsByDoctor(token,selectedDoctor!!.email)

        reviewViewModel.reviews.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            when(it){
                is Resource.Loading->{
                    (activity as PatientMainActivity).showLoading()
                }
                is Resource.Success->{
                    (activity as PatientMainActivity).hideLoading()
                    reviewAdapter!!.setData(it.data!! )

                }
                is Resource.Error->{
                    (activity as PatientMainActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

        binding.tvAboutDoctor.text=selectedDoctor!!.about


//        val workingTimeList=ArrayList<String>()
//        workingTimeList.add("Monday")
//        workingTimeList.add("Tuesday")
//        workingTimeList.add("Wednesday")
//        workingTimeList.add("Thursday")
//        workingTimeList.add("Friday")
//        workingTimeList.add("Saturday")
//        workingTimeList.add("Sunday")







        binding.btBookAppoinment.setOnClickListener{
            val bundle=Bundle()
            bundle.putParcelable(UtilConstants.doctor,selectedDoctor!!)
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_doctorDetails_to_bookAppointmentFragment,bundle)
        }
    }

    private fun createAdapter(){

        workingTimeAdapter = WorkingTimeAdapter(requireActivity())
        binding.rcvWorkingTime.adapter = workingTimeAdapter
        binding.rcvWorkingTime.layoutManager = LinearLayoutManager(requireActivity())
        workingTimeAdapter!!.setData(selectedDoctor!!.workingTimes)


        reviewAdapter = ReviewAdapter(requireActivity())
        binding.rcvReview.adapter = reviewAdapter
        binding.rcvReview.layoutManager = LinearLayoutManager(requireActivity())
    }



}