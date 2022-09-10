package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.AddMedicinePopupBinding
import com.medwiz.medwiz.databinding.FragmentAddPrescriptionBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import com.medwiz.medwiz.util.MedWizUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentAddPrescriptions : Fragment(R.layout.fragment_add_prescription) {
    private lateinit var binding: FragmentAddPrescriptionBinding
    private var adapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPrescriptionBinding.bind(view)

        binding.imgBack.setOnClickListener{

        }
        binding.imgAddPrescription.setOnClickListener{
            hideViews()
            unHidePrescriptionView()
        }

        binding.imgMedicine.setOnClickListener {

            adapter=null
             showAlertFilter(getString(R.string.add_medicine_title))

        }

        binding.imgTest.setOnClickListener {
            adapter=null
            showAlertFilter(getString(R.string.add_test_title))

        }
        binding.btAdd.setOnClickListener {
            if(medicineList.size>0 ||labTestList.size>0) {
                (activity as PrescriptionMainActivity).setPrescriptionData(medicineList,labTestList)
                (activity as PrescriptionMainActivity).openSendPrescription()
            }
        }



    }

    private fun unHidePrescriptionView() {
        binding.tvMedicine.visibility=View.VISIBLE
        binding.imgMedicine.visibility=View.VISIBLE
        binding.tvTest.visibility=View.VISIBLE
        binding.imgTest.visibility=View.VISIBLE
    }

    private fun hideViews() {
        binding.imgAddPrescription.visibility=View.GONE
    }

    private fun showAlertFilter(type:String) {
        lateinit var mAlert : AlertDialog
        val alertBinding = AddMedicinePopupBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext())
        mAlert = builder.setView(alertBinding.root)
            .setCancelable(false)
            .create()
        mAlert.show()
        mAlert.setCanceledOnTouchOutside(true)
        alertBinding.tvTitle.text=type
        var medicineName:String=""
        var noOfDays:String=""

        when(type){
            getString(R.string.add_medicine_title)->{
                alertBinding.tvTypeName.text=getString(R.string.medicine_name)
                alertBinding.rlMedicine.visibility=View.VISIBLE
            }
            getString(R.string.add_test_title)->{
                alertBinding.tvTypeName.text=getString(R.string.lab_test_name)
                alertBinding.rlMedicine.visibility=View.GONE
            }
        }
        alertBinding.imgAddMedicine.setOnClickListener {
            when(type){
                getString(R.string.add_medicine_title)->{
                      addMedicine(alertBinding,mAlert)
                }
                getString(R.string.add_test_title)->{
                    addLabTest(alertBinding,mAlert)
                }
            }


        }

    }

    private fun addMedicine(alertBinding:AddMedicinePopupBinding,mAlert: AlertDialog) {
        val medicineName=alertBinding.etMedicineName.text.toString()
        val noOfDays=alertBinding.etNoOfDays.text.toString()
        if(medicineName.isNotEmpty()&&noOfDays.isNotEmpty()){
            val medicine=Medicine((medicineList.size+1),alertBinding.etMedicineName.text.toString(),"",
                noOfDays.toInt(),
                false,false,false)

            if(alertBinding.btMorning.text.toString()=="M"){
                medicine.isMorning=true
            }
            if(alertBinding.btAfterNoon.text.toString()=="A"){
                medicine.isAfterNoon=true
            }
            if(alertBinding.btNight.text.toString()=="N"){
                medicine.isNight=true
            }
            medicineList.add(medicine)
            adapter = PrescriptionAdapter(requireContext(),getString(R.string.add_medicine_title))
            binding.rcvMedicine.adapter = adapter
            binding.rcvMedicine.layoutManager = LinearLayoutManager(requireContext())

            createList(mAlert,medicineList)

        }else{
           MedWizUtils.showErrorPopup(requireContext(),"Please Add values")
        }
    }

    private fun addLabTest(alertBinding:AddMedicinePopupBinding,mAlert: AlertDialog) {
        val medicineName=alertBinding.etMedicineName.text.toString()
        if(medicineName.isNotEmpty()){
            val labTest=Medicine((medicineList.size+1),"",alertBinding.etMedicineName.text.toString(),0,
                false,false,false)
            labTestList.add(labTest)
            adapter = PrescriptionAdapter(requireContext(),getString(R.string.add_test_title))
            binding.rcvTest.adapter = adapter
            binding.rcvTest.layoutManager = LinearLayoutManager(requireContext())

            createList(mAlert,labTestList)

        }else{
            MedWizUtils.showErrorPopup(requireContext(),"Please Add values")
        }
    }

    private fun createList(mAlert:AlertDialog,list:ArrayList<Medicine>) {
        mAlert.dismiss()
        adapter!!.setData(list)

    }
}