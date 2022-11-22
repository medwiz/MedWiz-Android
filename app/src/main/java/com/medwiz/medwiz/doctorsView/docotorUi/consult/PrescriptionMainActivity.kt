package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.ActivityPrescriptionMainBinding
import com.medwiz.medwiz.doctorsView.model.Medication
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.util.CustomLoaderDialog
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PrescriptionMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionMainBinding
    private var medicineList=ArrayList<Medication>()
    private var labTestList=ArrayList<Medication>()
    private var consultation:Consultation?=null
    private var userDetails: LoginResponse?=null
    private var mCustomLoader: CustomLoaderDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrescriptionMainBinding.inflate(layoutInflater)
        consultation=intent.getParcelableExtra<Consultation>(UtilConstants.consultation)
        userDetails=intent.getParcelableExtra<LoginResponse>(UtilConstants.userDetails)
        setContentView(binding.root)
        mCustomLoader = CustomLoaderDialog(this, true)
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

    fun setPrescriptionData(medicineList: ArrayList<Medication>, labTestList: ArrayList<Medication>) {
        this.medicineList=medicineList
        this.labTestList=labTestList

    }

    fun getMedicineList():ArrayList<Medication>{
        return medicineList
    }

    fun getTestList():ArrayList<Medication>{
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

    private fun setConsultation(consultation: Consultation){
        this.consultation=consultation
    }
    fun getConsultation():Consultation{
        return this.consultation!!
    }
    fun setUserDetails(userDetails:LoginResponse){
        this.userDetails=userDetails;
    }

    fun getUserDetails():LoginResponse{
        return this.userDetails!!
    }

    fun showLoading() {
        if (mCustomLoader?.window != null) {
            (mCustomLoader?.window)!!.setBackgroundDrawableResource(android.R.color.transparent)
            mCustomLoader?.show()
        }
    }

    fun hideLoading() {
        if (mCustomLoader != null) mCustomLoader?.cancel()
    }

}