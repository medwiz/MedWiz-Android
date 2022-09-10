package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivitySendPrescriptionBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SendPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendPrescriptionBinding
    private var medicineAdapter: PrescriptionAdapter?=null
    private var labAdapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra("myBundle")
        medicineList = bundle!!.getParcelableArrayList("med")!!
        labTestList=bundle.getParcelableArrayList("lab")!!
        binding.imgBack.setOnClickListener{
            finish()
        }
        binding.btSend.setOnClickListener{
            finish()
        }
        binding.tvPreview.setOnClickListener{
            val mainFragment: PreviewPrescriptionFragment = PreviewPrescriptionFragment()
             changeFragment(mainFragment)
        }

        medicineAdapter = PrescriptionAdapter(this,getString(R.string.add_medicine_title))
        binding.rcvMedicine.adapter = medicineAdapter
        binding.rcvMedicine.layoutManager = LinearLayoutManager(this)
        medicineAdapter!!.setData(medicineList)


        labAdapter = PrescriptionAdapter(this,getString(R.string.add_test_title))
        binding.rcvTest.adapter = labAdapter
        binding.rcvTest.layoutManager = LinearLayoutManager(this)
        labAdapter!!.setData(labTestList)
    }

    private fun changeFragment(fr: Fragment) {
        val fl = binding.frameLayout
        fl.removeAllViews()
        val transaction1 = supportFragmentManager.beginTransaction()
        transaction1.add(R.id.container, fr)
        transaction1.commit()
    }
}