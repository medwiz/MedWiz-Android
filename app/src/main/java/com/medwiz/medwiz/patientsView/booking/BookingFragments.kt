package com.medwiz.medwiz.patientsView.patientsUi.booking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPatientBookingBinding
import com.medwiz.medwiz.model.BookingModel
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class BookingFragments:Fragment(R.layout.fragment_patient_booking),BookingItemListener {
    private var adapter: PatientBookingAdapter?=null
    private lateinit var binding: FragmentPatientBookingBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPatientBookingBinding.bind(view)
        binding.tvUpComingBooking.setOnClickListener {  }
        binding.tvPastBooking.setOnClickListener {  }
        val profileItem1= BookingModel(UtilConstants.ITEM_PROFILE,"Profile","","","","","")
        val profileItem2= BookingModel(UtilConstants.ITEM_EDIT_PROFILE,"Edit Profile","","","","","")
        val profileItem3= BookingModel(UtilConstants.ITEM_SETTING,"Setting","","","","","")
        val profileItem4= BookingModel(UtilConstants.ITEM_TERMS,"Terms & Privacy Policy","","","","","")
        val lis= ArrayList<BookingModel>()
        lis.add(profileItem1)
        lis.add(profileItem2)
        lis.add(profileItem3)
        lis.add(profileItem4)
        adapter = PatientBookingAdapter(requireActivity(),lis,this)
        binding.rcvBooking.adapter = adapter
        binding.rcvBooking.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickDate(position: Int, dateObj: BookingModel) {

    }
}