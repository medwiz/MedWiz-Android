package com.medwiz.medwiz.auth.signUp
import android.os.Bundle
import android.view.View
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
import com.medwiz.medwiz.databinding.FragmentAddLabInfoBinding
import com.medwiz.medwiz.databinding.FragmentAddMedicineInfoBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddLabTestInfoFragment:Fragment(R.layout.fragment_add_lab_info) {

    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentAddLabInfoBinding
    var typeList = arrayOf("Tablet","Drops","Syrup","Inhalers","Injections","others")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddLabInfoBinding.bind(view)

        binding.imgBackAddInfo.setOnClickListener{

            findNavController().navigateUp()


        }

        binding.btAdd.setOnClickListener {
            if(binding.etLabTestName.text.toString().isNotEmpty()&&binding.etPrice.text.toString().isNotEmpty()){
             val name=binding.etLabTestName.text.toString()
             val price=binding.etPrice.text.toString()
            val labTestObj=JsonObject()
            labTestObj.addProperty("name",name)
            labTestObj.addProperty("price",price)
            viewModel.addLabTest(labTestObj)
            }
        }









        viewModel.addMedicine.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    Toast.makeText(requireContext(),"Lab test Added successfully!",Toast.LENGTH_SHORT).show()
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



    private fun clearAll(){
        binding.etLabTestName.setText("")
        binding.etPrice.setText("")

    }



}