package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private var token:String=""
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
           createPrescription()

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
                        Toast.makeText(requireContext(),"Prescription created", Toast.LENGTH_SHORT).show()
                        updateConsultation(it.data.id)

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
        consultationViewModel.consultation.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as PrescriptionMainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as PrescriptionMainActivity).hideLoading()
                    if(it.data!!.id>0){
                        Toast.makeText(requireContext(),"consultation updated successfully", Toast.LENGTH_SHORT).show()
                        goToDoctorActivity()

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

    private fun goToDoctorActivity() {
        val intent = Intent (requireActivity(), DoctorsActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }
    
    private fun createPrescription(){

        val jsonObject=JsonObject()
        jsonObject.addProperty("addedDate",MedWizUtils.getCurrentDate())
        jsonObject.addProperty("isActive",true)
        jsonObject.addProperty("updateDate",MedWizUtils.getCurrentDate())
        jsonObject.addProperty("patientId",consultation!!.patientId)
        jsonObject.addProperty("patientName",consultation!!.patientName)
        jsonObject.addProperty("docId",consultation!!.docId)
        jsonObject.addProperty("specialization",consultation!!.specialization)
        jsonObject.addProperty("age",consultation!!.age)
        jsonObject.addProperty("weight",0)
        jsonObject.addProperty("gender",userDetails!!.gender)
        val docName=userDetails!!.firstname+" "+userDetails!!.lastname
        jsonObject.addProperty("docName",docName)
        val medicationArray= JsonArray()
        for (i in 0 until medicineList.size) {
            val medicineObj=JsonObject()
            medicineObj.addProperty("noOfDays",medicineList[i].noOfDays)
            medicineObj.addProperty("morningDose",medicineList[i].morningDose)
            medicineObj.addProperty("afternoonDose",medicineList[i].afternoonDose)
            medicineObj.addProperty("nightDose",medicineList[i].nightDose)
            medicineObj.addProperty("name",medicineList[i].name)
            medicationArray.add(medicineObj)
        }
        val medicationLabArray= JsonArray()
        for (i in 0 until labTestList.size) {
            val medicineLabObj=JsonObject()
            medicineLabObj.addProperty("name",labTestList[i].labTestName)
            medicationLabArray.add(medicineLabObj)
        }
        jsonObject.add("medicationLabs",medicationLabArray)

        jsonObject.add("medications",medicationArray)
        token=MedWizUtils.storeValueInPreference(requireContext(),UtilConstants.accessToken,"",false)
        prescriptionViewModel.createPrescription(token,jsonObject)

    }

    private fun updateConsultation(prescriptionId:Long) {
        val requestObj=JsonObject()
        requestObj.addProperty("addedDate",consultation!!.addedDate)
        requestObj.addProperty("consDate",consultation!!.consDate)
        requestObj.addProperty("consTime",consultation!!.consTime)
        requestObj.addProperty("docId",consultation!!.docId)
        requestObj.addProperty("fees",consultation!!.fees)
        requestObj.addProperty("filePath",consultation!!.filePath)
        requestObj.addProperty("isActive",consultation!!.isActive)
        requestObj.addProperty("isCash",consultation!!.isCash)
        requestObj.addProperty("laboratoryId",consultation!!.laboratoryId)
        requestObj.addProperty("pharmaId",consultation!!.pharmaId)
        requestObj.addProperty("status",UtilConstants.STATUS_COMPLETED)
        requestObj.addProperty("transactionId",consultation!!.transactionId)
        requestObj.addProperty("patientId",consultation!!.patientId)
        requestObj.addProperty("patientMobile",consultation!!.patientMobile)
        requestObj.addProperty("patientGender",consultation!!.patientGender)
        requestObj.addProperty("patientName",consultation!!.patientName)
        requestObj.addProperty("prescriptionId",prescriptionId)
        requestObj.addProperty("age",consultation!!.age)
        consultationViewModel.update(token,requestObj,consultation!!.id)

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