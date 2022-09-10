package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityPrescriptionMainBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PrescriptionMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionMainBinding
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrescriptionMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openAddPrescriptionFragment()
    }

    private fun openAddPrescriptionFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, FragmentAddPrescriptions()).commit()

    }

     fun openPreviewPrescription() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, PreviewPrescriptionFragment()).commit()

    }

     fun openSendPrescription() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, FragmentSendPrescription()).commit()

    }

    fun setPrescriptionData(medicineList: ArrayList<Medicine>, labTestList: ArrayList<Medicine>) {
        this.medicineList=medicineList
        this.labTestList=labTestList

    }

    fun getMedicineList():ArrayList<Medicine>{
        return medicineList
    }

    fun getTestList():ArrayList<Medicine>{
        return labTestList
    }


}