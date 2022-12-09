package com.medwiz.medwiz.auth.signUp
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PackageManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentAddDocInfoBinding
import com.medwiz.medwiz.databinding.FragmentAddMedicineInfoBinding
import com.medwiz.medwiz.databinding.WorkingTimeDialogBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.model.*
import com.medwiz.medwiz.patientsView.booking.doctorDetails.WorkingTimeAdapter
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.util.*
import com.medwiz.medwiz.viewmodels.FileViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileDescriptor


@AndroidEntryPoint
class AddMedicineInfoFragment:Fragment(R.layout.fragment_add_medicine_info) {

    private val viewModel: AuthViewModel by viewModels()
    private var strType=""
    private lateinit var binding: FragmentAddMedicineInfoBinding
    var typeList = arrayOf("Tablet","Drops","Syrup","Inhalers","Injections","others")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddMedicineInfoBinding.bind(view)

        binding.imgBackAddInfo.setOnClickListener{

            findNavController().navigateUp()


        }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, typeList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerMedicineType.adapter = aa

        binding.spinnerMedicineType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                strType=typeList[position]
            }

        }
        binding.btAdd.setOnClickListener {
            val medName=binding.etMedicineName.text.toString()
            val brandName=binding.etBrandName.text.toString()
            val mrp=binding.etMrp.text.toString()

            if(medName.isNotEmpty()&&brandName.isNotEmpty()&&mrp.isNotEmpty()&&strType.isNotEmpty()){
                val medicineObj=JsonObject()
                medicineObj.addProperty("name",medName)
                medicineObj.addProperty("type",strType)
                medicineObj.addProperty("brand",brandName)
                medicineObj.addProperty("price",mrp)
                medicineObj.addProperty("isActive",true)
                 viewModel.addMedicine(medicineObj)
            }else{
                MedWizUtils.showErrorPopup(requireContext(),"Please fill all details")
            }


        }

        viewModel.addMedicine.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    Toast.makeText(requireContext(),"Medicine Added successfully!",Toast.LENGTH_SHORT).show()
                    clearAll()

                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }

                }
            }
        })

    }

    private fun clearAll() {
        binding.etMedicineName.setText("")
        binding.etBrandName.setText("")
        binding.etMrp.setText("")
        strType=""
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, typeList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerMedicineType.adapter = aa
    }


}