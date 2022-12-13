package com.medwiz.medwiz.auth.signUp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.data.reponse.MedicineResponse
import com.medwiz.medwiz.databinding.FragmentAddMedicineInfoBinding
import com.medwiz.medwiz.databinding.UpdateMedicineFragmentBinding
import com.medwiz.medwiz.doctorsView.docotorUi.consult.OnSearchItemListener
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionMainActivity
import com.medwiz.medwiz.doctorsView.docotorUi.consult.SearchAdapter
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateMedicineFragment : Fragment(R.layout.update_medicine_fragment),
    OnSearchItemListener {
    private var adapter: AddMedicineAdapter? = null
    private val viewModel: AuthViewModel by viewModels()
    private var searchAdapter: SearchAdapter? = null
    private val searchViewModel: SearchViewModel by viewModels()
    private var strType = ""
    private var originalName=""
    private lateinit var binding: UpdateMedicineFragmentBinding
    var typeList = arrayOf("Select Type","Tablet", "Drops", "Syrup", "Inhalers", "Injections", "Cream","Ointment","Lotion","Powder","Spray","Nasal Spray",
        "Nasal Drop","Gel")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpdateMedicineFragmentBinding.bind(view)

        binding.imgBack.setOnClickListener {

            findNavController().navigateUp()


        }

        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, typeList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerType.adapter = aa

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               if(position>0){
                strType = typeList[position]
               }
            }

        }

        binding.btUpdate.setOnClickListener {
            if(originalName.isEmpty()){
                Toast.makeText(
                    requireContext(),
                    "Add medicine name only from suggestion list!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.etMedicineName.text.toString()
                    .isNotEmpty() && binding.etDosage.text.toString()
                    .isNotEmpty() && strType.isNotEmpty()&&originalName.isNotEmpty()
            ) {
                val dosage = binding.etDosage.text.toString();
                val medicineObj = JsonObject()
                medicineObj.addProperty("medicine_name", originalName)
                medicineObj.addProperty("dosage", dosage)
                medicineObj.addProperty("type", strType)
                viewModel.addMedicine(medicineObj, true)
            }
        }

        binding.etMedicineName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(e: Editable?) {
                if (e!!.length > 1) {
                    searchViewModel.searchMedicine("name",e.toString().trim(),true)
                }
                if (e.isEmpty() && searchAdapter != null) {
                    searchAdapter!!.searchList.clear()
                    searchAdapter!!.notifyDataSetChanged()
                    binding.rcvSearch.visibility=View.VISIBLE
                }
            }

        })

        searchViewModel.medicine.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success -> {
                    (activity as MainActivity).hideLoading()
                    if (it.data!!.size > 0) {
                        this.searchAdapter = SearchAdapter(requireContext(), it.data, this,true,true)
                        this.binding.rcvSearch.adapter = searchAdapter
                    }

                }

                is Resource.Error -> {
                    (activity as MainActivity).hideLoading()
                }
            }
        })

        viewModel.addMedicine.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success -> {
                    (activity as MainActivity).hideLoading()
                    Toast.makeText(
                        requireContext(),
                        "Medicine updated successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    clearAll()

                }
                is Resource.Error -> {
                    (activity as MainActivity).hideLoading()
                    if (it.message == UtilConstants.unauthorized) {
                        MedWizUtils.performLogout(requireContext(), requireActivity())
                    }

                }
            }
        })

    }


    private fun addViews() {
//        val parent = LinearLayout(requireContext())
//        parent.layoutParams =
//            LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
//        parent.orientation = LinearLayout.HORIZONTAL
//        val etBrandName=EditText(requireContext())
//        val etMrp=EditText(requireContext())
//        parent.addView(etBrandName)
//        parent.addView(etMrp)


    }


    private fun clearAll() {
        binding.etMedicineName.setText("")
        originalName=""
        strType=""
        binding.rcvSearch.visibility=View.VISIBLE
    }


    override fun onItemClick(obj: MedicineResponse, position: Int) {
        binding.etMedicineName.setText(obj.name)
        binding.etDosage.setText(obj.dosage)
        originalName=obj.name
        searchAdapter!!.searchList.clear()
        searchAdapter!!.notifyDataSetChanged()
        binding.rcvSearch.visibility=View.GONE
    }


}