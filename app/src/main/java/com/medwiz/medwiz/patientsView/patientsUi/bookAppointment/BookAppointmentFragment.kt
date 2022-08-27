package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentBookAppointmentBinding
import com.medwiz.medwiz.patientsView.booking.doctorDetails.ReviewAdapter
import com.medwiz.medwiz.patientsView.booking.doctorDetails.SelectDateAdapter
import com.medwiz.medwiz.patientsView.booking.doctorDetails.SelectTimeAdapter
import com.medwiz.medwiz.patientsView.booking.doctorDetails.WorkingTimeAdapter
import com.medwiz.medwiz.patientsView.patientModels.ReviewModel
import com.medwiz.medwiz.util.UtilConstants
import java.util.*

class BookAppointmentFragment : Fragment(R.layout.fragment_book_appointment) {
    private lateinit var binding: FragmentBookAppointmentBinding
    private var selectTimeAdapter: SelectTimeAdapter?=null
    private var selectDateAdapter: SelectDateAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookAppointmentBinding.bind(view)
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

        selectDateAdapter = SelectDateAdapter(requireActivity(),lis)
        binding.rcvSelectDate.adapter = selectDateAdapter
        binding.rcvSelectDate.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        selectTimeAdapter = SelectTimeAdapter(requireActivity(),lis)
        binding.rcvSelectTime.adapter = selectTimeAdapter
        binding.rcvSelectTime.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)





        binding.btContinue.setOnClickListener{
            val bundle=Bundle()
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_bookAppointmentFragment_to_paymentFragment,bundle)
        }
    }



}