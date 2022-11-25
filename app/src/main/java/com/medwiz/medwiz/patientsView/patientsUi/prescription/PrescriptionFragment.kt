package com.medwiz.medwiz.patientsView.patientsUi.prescription

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPrescriptionBinding
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionMainActivity
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.patientsView.patientsUi.doctorDetails.ReviewAdapter
import com.medwiz.medwiz.patientsView.patientsUi.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PrescriptionFragment:Fragment(R.layout.fragment_prescription) {
    private var prescriptionAdapter: PrescriptionAdapter?=null
    private lateinit var binding: FragmentPrescriptionBinding
    private val prescriptionViewModel: PrescriptionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrescriptionBinding.bind(view)
        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        createAdapter()

        prescriptionViewModel.getPrescriptionList(token,(activity as PatientMainActivity).getUserDetails().id)

        prescriptionViewModel.prescriptionList.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Loading->{
                    (activity as PatientMainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as PatientMainActivity).hideLoading()
                     prescriptionAdapter!!.setData(it.data!!)

                }
                is Resource.Error->{
                    (activity as PatientMainActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }

                }
            }
        })
    }

    private fun createAdapter() {
        prescriptionAdapter = PrescriptionAdapter(requireActivity())
        binding.rcvPrescription.adapter = prescriptionAdapter
        binding.rcvPrescription.layoutManager = LinearLayoutManager(requireActivity())

    }
}