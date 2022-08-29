package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityAddPrescriptionsBinding
import com.medwiz.medwiz.databinding.AddMedicinePopupBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import com.medwiz.medwiz.util.MedWizUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPrescriptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPrescriptionsBinding
    private var adapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPrescriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener{
            finish()
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
            val intent = Intent (this, SendPrescriptionActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelableArrayList("med",medicineList)
            bundle.putParcelableArrayList("lab",labTestList)
            intent.putExtra("myBundle",bundle)
            startActivity(intent)




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
        val alertBinding = AddMedicinePopupBinding.inflate(LayoutInflater.from(this))
        val builder = AlertDialog.Builder(this)
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
            adapter = PrescriptionAdapter(this,getString(R.string.add_medicine_title))
            binding.rcvMedicine.adapter = adapter
            binding.rcvMedicine.layoutManager = LinearLayoutManager(this)

            createList(mAlert,medicineList)

        }else{
           MedWizUtils.showErrorPopup(this,"Please Add values")
        }
    }

    private fun addLabTest(alertBinding:AddMedicinePopupBinding,mAlert: AlertDialog) {
        val medicineName=alertBinding.etMedicineName.text.toString()
        if(medicineName.isNotEmpty()){
            val labTest=Medicine((medicineList.size+1),"",alertBinding.etMedicineName.text.toString(),0,
                false,false,false)
            labTestList.add(labTest)
            adapter = PrescriptionAdapter(this,getString(R.string.add_test_title))
            binding.rcvTest.adapter = adapter
            binding.rcvTest.layoutManager = LinearLayoutManager(this)

            createList(mAlert,labTestList)

        }else{
            MedWizUtils.showErrorPopup(this,"Please Add values")
        }
    }

    private fun createList(mAlert:AlertDialog,list:ArrayList<Medicine>) {
        mAlert.dismiss()
        adapter!!.setData(list)

    }
}