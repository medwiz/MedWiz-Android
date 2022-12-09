package com.medwiz.medwiz.patientsView.booking

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPatientBookingBinding
import com.medwiz.medwiz.model.BookingModel
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.patientsView.patientsUi.booking.BookingItemListener
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragments:Fragment(R.layout.fragment_patient_booking), BookingItemListener {
    private var adapter: PatientBookingAdapter?=null
    private lateinit var binding: FragmentPatientBookingBinding
    var token:String=""
    var userId:Long=0
    var email:String=""
    var userType:String=""
    private val consultationViewModel: ConsultationViewModel by viewModels()
    var bookingListType = arrayOf(UtilConstants.STATUS_UPCOMING,UtilConstants.STATUS_COMPLETED, UtilConstants.STATUS_CANCELED)
    private var strBookingType:String=UtilConstants.TYPE_UPCOMING
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPatientBookingBinding.bind(view)
         token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
         userType= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userType,"",false)
         userId= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"0",false).toLong()
         email= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.email,"",false)
        consultationViewModel.getConsultationByPatientId(token,userId,strBookingType)
      consultationViewModel.consultationList.observe(viewLifecycleOwner, Observer {
          when(it){
              is Resource.Loading->{
                  (activity as PatientMainActivity).showLoading()
              }
              is Resource.Success->{
                  (activity as PatientMainActivity).hideLoading()

                  adapter = PatientBookingAdapter(requireActivity(),it.data!!,this,strBookingType)
                  binding.rcvBooking.adapter = adapter
                  binding.rcvBooking.layoutManager = LinearLayoutManager(requireActivity())

              }
              is Resource.Error->{
                  (activity as PatientMainActivity).hideLoading()
                  MedWizUtils.showErrorPopup(
                      requireActivity(),
                      it.message.toString())
              }
          }
      })

        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bookingListType)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerShop.adapter = aa

        binding.spinnerShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                    strBookingType=bookingListType[position]
                    consultationViewModel.getConsultationByPatientId(token,userId,strBookingType)


            }
    }


}

    override fun onClickDate(position: Int, dateObj: BookingModel) {

    }
}