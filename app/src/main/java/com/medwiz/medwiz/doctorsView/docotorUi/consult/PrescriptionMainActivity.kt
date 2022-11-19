package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityPrescriptionMainBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PrescriptionMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionMainBinding
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()
    private var consultation:Consultation?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrescriptionMainBinding.inflate(layoutInflater)
        consultation=intent.getParcelableExtra<Consultation>(UtilConstants.consultation)
        setContentView(binding.root)
        openAddPrescriptionFragment()
    }

     fun openAddPrescriptionFragment() {
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
           // Log.i(TAG, "=============onBackPressed - Popping backstack====")
            supportFragmentManager.popBackStack()
        } else {
           // Log.i(TAG, "=============onBackPressed called because nothing on backstack====")
            super.onBackPressed()
        }
    }


}