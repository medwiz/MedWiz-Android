package com.medwiz.medwiz.patientsView.patientsUi.prescription

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPrescriptionBinding
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.patientsView.booking.PrescriptionItemListener
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrescriptionFragment:Fragment(R.layout.fragment_prescription) ,PrescriptionItemListener{
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
        prescriptionAdapter = PrescriptionAdapter(requireActivity(),this)
        binding.rcvPrescription.adapter = prescriptionAdapter
        binding.rcvPrescription.layoutManager = LinearLayoutManager(requireActivity())

    }

    override fun onClickPrescription(position: Int, itemObj: Prescription) {
        val bundle=Bundle()
        bundle.putParcelable(UtilConstants.prescription,itemObj)
        findNavController().navigate(R.id.action_prescriptionFragment_to_prescriptionDetailsFragment,bundle)
    }
}