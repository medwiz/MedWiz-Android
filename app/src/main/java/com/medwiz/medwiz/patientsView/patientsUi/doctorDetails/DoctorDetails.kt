package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorDetailsBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientModels.ReviewModel
import com.medwiz.medwiz.util.UtilConstants
import java.util.*

class DoctorDetails : Fragment(R.layout.fragment_doctor_details) {
    private lateinit var binding: FragmentDoctorDetailsBinding
    private var reviewAdapter: ReviewAdapter?=null
    private var workingTimeAdapter:WorkingTimeAdapter?=null
    private var selectedDoctor:DoctorResponse?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorDetailsBinding.bind(view)
        selectedDoctor=arguments?.getParcelable<DoctorResponse>(UtilConstants.doctor)!!
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }

        val d= ReviewModel("1","5",
            "Very Good","Tom","",requireActivity().getString(R.string.long_text_ex))
        val d1= ReviewModel("2","3",
            "Awesome","Hrick","",requireActivity().getString(R.string.long_text_ex))
        val d2= ReviewModel("3","4",
            "Very Good","Sam","",requireActivity().getString(R.string.long_text_ex))
        val lis= ArrayList<ReviewModel>()
        lis.add(d)
        lis.add(d1)
        lis.add(d2)

        val workingTimeList=ArrayList<String>()
        workingTimeList.add("Monday")
        workingTimeList.add("Tuesday")
        workingTimeList.add("Wednesday")
        workingTimeList.add("Thursday")
        workingTimeList.add("Friday")
        workingTimeList.add("Saturday")
        workingTimeList.add("Sunday")

        workingTimeAdapter = WorkingTimeAdapter(requireActivity(),workingTimeList)
        binding.rcvWorkingTime.adapter = workingTimeAdapter
        binding.rcvWorkingTime.layoutManager = LinearLayoutManager(requireActivity())


        reviewAdapter = ReviewAdapter(requireActivity(),lis)
        binding.rcvReview.adapter = reviewAdapter
        binding.rcvReview.layoutManager = LinearLayoutManager(requireActivity())


        binding.btBookAppoinment.setOnClickListener{
            val bundle=Bundle()
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_doctorDetails_to_bookAppointmentFragment,bundle)
        }
    }



}