package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityAddPrescriptionsBinding
import com.medwiz.medwiz.databinding.ActivityDoctorsBinding
import com.medwiz.medwiz.databinding.AddMedicinePopupBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPrescriptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPrescriptionsBinding
    private var medicineList=ArrayList<Medicine>()
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
             showAlertFilter()

        }
    }

    private fun unHidePrescriptionView() {
       binding.tvTitle.visibility=View.VISIBLE
        binding.tvMedicine.visibility=View.VISIBLE
        binding.imgMedicine.visibility=View.VISIBLE
        binding.tvTest.visibility=View.VISIBLE
        binding.imgTest.visibility=View.VISIBLE
    }

    private fun hideViews() {
        binding.imgAddPrescription.visibility=View.GONE
        binding.layMainInclude.root.visibility = View.GONE
    }

    private fun showAlertFilter() {
        lateinit var mAlert : AlertDialog
        val alertBinding = AddMedicinePopupBinding.inflate(LayoutInflater.from(this))
        val builder = AlertDialog.Builder(this)
        mAlert = builder.setView(alertBinding.root)
            .setCancelable(false)
            .create()
        mAlert.show()
        mAlert.setCanceledOnTouchOutside(true)
        var medicineName:String=""
        var noOfDays:String=""
        alertBinding.imgAddMedicine.setOnClickListener {
            medicineName=alertBinding.etMedicineName.text.toString()
            noOfDays=alertBinding.etNoOfDays.text.toString()
            if(medicineName.isNotEmpty()&&noOfDays.isNotEmpty()){
                val medicine=Medicine(alertBinding.etMedicineName.text.toString(),
                    noOfDays.toInt(),
                    false,false,false)

                if(alertBinding.btMorning.text.toString()=="Morning"){
                medicine.isMorning=true
                }
                if(alertBinding.btAfterNoon.text.toString()=="Afternoon"){
                    medicine.isAfterNoon=true
                }
                if(alertBinding.btNight.text.toString()=="Night"){
                    medicine.isNight=true
                }
                medicineList.add(medicine)
                createList(mAlert)

            }else{
                val i=0
            }
        }

    }

    private fun createList(mAlert:AlertDialog) {
        mAlert.dismiss()

    }
}