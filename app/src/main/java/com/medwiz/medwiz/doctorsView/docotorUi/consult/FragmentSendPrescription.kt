package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentSendPrescriptionBinding
import com.medwiz.medwiz.doctorsView.model.Medicine
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentSendPrescription :Fragment(R.layout.fragment_send_prescription) {
    private lateinit var binding: FragmentSendPrescriptionBinding
    private var medicineAdapter: PrescriptionAdapter?=null
    private var labAdapter: PrescriptionAdapter?=null
    private var medicineList=ArrayList<Medicine>()
    private var labTestList=ArrayList<Medicine>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendPrescriptionBinding.bind(view)
        medicineList =  (activity as PrescriptionMainActivity).getMedicineList()
        labTestList= (activity as PrescriptionMainActivity).getTestList()
        binding.imgBack.setOnClickListener{

        }
        binding.btSend.setOnClickListener{
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


}