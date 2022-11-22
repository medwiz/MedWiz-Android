package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentSendPrescriptionBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.doctorsView.model.Medication
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import com.medwiz.medwiz.viewmodels.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentSendPrescription :Fragment(R.layout.fragment_send_prescription) {
    private lateinit var binding: FragmentSendPrescriptionBinding
    private var medicineAdapter: PrescriptionAdapter?=null
    private var labAdapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medication>()
    private var labTestList=ArrayList<Medication>()
    private var consultation: Consultation?=null
    private var userDetails: LoginResponse?=null
    private val consultationViewModel: ConsultationViewModel by viewModels()
    private val prescriptionViewModel: PrescriptionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendPrescriptionBinding.bind(view)
        medicineList =  (activity as PrescriptionMainActivity).getMedicineList()
        labTestList= (activity as PrescriptionMainActivity).getTestList()
        consultation=(activity as PrescriptionMainActivity).getConsultation()
        userDetails=(activity as PrescriptionMainActivity).getUserDetails()
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


        prescriptionViewModel.prescription.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as PrescriptionMainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as PrescriptionMainActivity).hideLoading()
                    if(it.data!!.id>0){
                        val i=0
                    }

                }
                is Resource.Error->{
                    (activity as PrescriptionMainActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }

                }
            }
        })
    }

    private fun updateConsultation() {
      val jsonObject=JsonObject()
        jsonObject.addProperty("addedDate",MedWizUtils.getCurrentDate())
        jsonObject.addProperty("isActive",true)
        jsonObject.addProperty("updateDate",MedWizUtils.getCurrentDate())
        jsonObject.addProperty("patientId",consultation!!.patientId)
        jsonObject.addProperty("docId",consultation!!.docId)
        val docName=userDetails!!.firstname+" "+userDetails!!.lastname
        jsonObject.addProperty("docName",docName)
        val medicationArray= JsonArray()
        for (i in 0 until medicineList.size) {
            val medicineObj=JsonObject()
            medicineObj.addProperty("noOfDays",medicineList[i].noOfDays)
            medicineObj.addProperty("morningDose",medicineList[i].morningDose)
            medicineObj.addProperty("afternoonDose",medicineList[i].afternoonDose)
            medicineObj.addProperty("nightDose",medicineList[i].nightDose)
            medicationArray.add(medicineObj)
        }
        val medicationLabArray= JsonArray()
        for (i in 0 until labTestList.size) {
            val medicineLabObj=JsonObject()
            medicineLabObj.addProperty("noOfDays",labTestList[i].labTestName)
            medicationLabArray.add(medicineLabObj)
        }
        jsonObject.add("medicationLab",medicationLabArray)

        jsonObject.add("medication",medicationArray)
        val token=MedWizUtils.storeValueInPreference(requireContext(),UtilConstants.accessToken,"",false)
        prescriptionViewModel.createPrescription(token,jsonObject)

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