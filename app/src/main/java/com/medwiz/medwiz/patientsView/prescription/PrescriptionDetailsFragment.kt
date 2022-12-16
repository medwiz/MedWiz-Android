package com.medwiz.medwiz.patientsView.prescription

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPrescriptionDetailsBinding
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionAdapter
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionListener
import com.medwiz.medwiz.model.Medication

import com.medwiz.medwiz.model.Prescription

import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PrescriptionDetailsFragment : Fragment(R.layout.fragment_prescription_details),PrescriptionListener {

    private lateinit var binding: FragmentPrescriptionDetailsBinding
    private val prescriptionViewModel: PrescriptionViewModel by viewModels()
    private var medicineAdapter: PrescriptionAdapter? = null
    private var labAdapter: PrescriptionAdapter? = null
    private var prescriptionObj: Prescription? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrescriptionDetailsBinding.bind(view)
        prescriptionObj = arguments?.getParcelable<Prescription>(UtilConstants.prescription)!!
        val token = MedWizUtils.storeValueInPreference(
            requireContext(),
            UtilConstants.accessToken, "", false
        )
        binding.imgBack.setOnClickListener {

            findNavController().navigateUp()

        }
        binding.tvOrderMedicine.setOnClickListener {
            if (prescriptionObj!!.medications.size > 0) {
                goToShop()
            }


        }
        binding.tvOrderLabTest.setOnClickListener {

           if( prescriptionObj!!.medicationLabs.size > 0){
               goToShop()
           }
        }

        setUi()


    }

    private fun goToShop(){
        //SelectShopFragment
        val bundle=Bundle()
        bundle.putParcelable(UtilConstants.prescription,prescriptionObj)
        findNavController().navigate(R.id.action_prescriptionDetailsFragment_to_selectShopFragment,bundle)
    }

    private fun setUi() {
        binding.tvDoctorName.text = prescriptionObj!!.docName
        binding.tvPatientName.text = prescriptionObj!!.patientName
        binding.tvPatientAge.text=prescriptionObj!!.age.toString()
//        binding.tvPatientWeight.text=prescriptionObj!!.weight
        binding.tvPrescriptionId.text = "Id: " + prescriptionObj!!.id
        binding.tvDate.text = prescriptionObj!!.updateDate
        // binding..text=prescriptionObj!!.specialization

        if (prescriptionObj!!.medications.size > 0) {
            binding.rlMedicineTitle.visibility = View.VISIBLE
            medicineAdapter =
                PrescriptionAdapter(requireContext(), getString(R.string.add_medicine_title),this)
            binding.rcvMedicine.adapter = medicineAdapter
            binding.rcvMedicine.layoutManager = LinearLayoutManager(requireContext())
            medicineAdapter!!.setData(prescriptionObj!!.medications)
        }
        if (prescriptionObj!!.medicationLabs.size > 0) {
            binding.rlTestTitle.visibility = View.VISIBLE
            val labList = ArrayList<Medication>()
            for (i in 0 until prescriptionObj!!.medicationLabs.size) {
                val medicineLab = Medication()
                medicineLab.name = prescriptionObj!!.medicationLabs[i].name
                labList.add(medicineLab)
            }
            labAdapter = PrescriptionAdapter(requireContext(), getString(R.string.add_test_title),this)
            binding.rcvTest.adapter = labAdapter
            binding.rcvTest.layoutManager = LinearLayoutManager(requireContext())
            labAdapter!!.setData(labList)
        }
    }

    override fun onClickDosage(obj: Medication, position: Int) {
        MedWizUtils.showErrorPopup(requireContext(),obj.dosage)
    }


}