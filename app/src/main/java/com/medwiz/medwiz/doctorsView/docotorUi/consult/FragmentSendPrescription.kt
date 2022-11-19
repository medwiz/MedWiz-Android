package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentSendPrescriptionBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentSendPrescription :Fragment(R.layout.fragment_send_prescription) {
    private lateinit var binding: FragmentSendPrescriptionBinding
    private var medicineAdapter: PrescriptionAdapter?=null
    private var labAdapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()
    private var consultation: Consultation?=null
    private val consultationViewModel: ConsultationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendPrescriptionBinding.bind(view)
        medicineList =  (activity as PrescriptionMainActivity).getMedicineList()
        labTestList= (activity as PrescriptionMainActivity).getTestList()
        consultation=(activity as PrescriptionMainActivity).getConsultation()

        if(consultation!=null){
            setUi()
        }

        binding.imgBack.setOnClickListener{
             goBack()
        }
        binding.btSend.setOnClickListener{
            updateConsultation()
        }
        binding.tvPreview.setOnClickListener{
            (activity as PrescriptionMainActivity).openPreviewPrescription()
        }

        medicineAdapter = PrescriptionAdapter(requireContext(),getString(R.string.add_medicine_title))
        binding.rcvMedicine.adapter = medicineAdapter
        binding.rcvMedicine.layoutManager = LinearLayoutManager(requireContext())
        medicineAdapter!!.setData(medicineList)


        labAdapter = PrescriptionAdapter(requireContext(),getString(R.string.add_test_title))
        binding.rcvTest.adapter = labAdapter
        binding.rcvTest.layoutManager = LinearLayoutManager(requireContext())
        labAdapter!!.setData(labTestList)
    }

    private fun updateConsultation() {

    }

    private fun setUi() {
        binding.includePatient.tvPatientName.text=consultation!!.patientName
        binding.includePatient.tvPatientGenderAndMobile.text= consultation!!.patientGender+" | "+consultation!!.patientMobile
        binding.includePatient.tvAge.text="Age:"+consultation!!.age
        binding.includePatient.tvDayAndDate.text=consultation!!.consDate
        binding.includePatient.tvTime.text=consultation!!.consTime
    }

    private fun goBack(){

        val fm=requireActivity().supportFragmentManager
        fm.popBackStack()
        (activity as PrescriptionMainActivity).openAddPrescriptionFragment()
    }


}