package com.medwiz.medwiz.auth.signUp
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.databinding.FragmentAddShopInfoBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.viewmodels.DoctorViewModel
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.util.*
import com.medwiz.medwiz.viewmodels.FileViewModel

import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class AddShopInfoFragment:Fragment(R.layout.fragment_add_shop_info) {
    private val viewModel: AuthViewModel by viewModels()
    private val doctorViewModel: DoctorViewModel by viewModels()
    var shopList = arrayOf("Select type","Pharmacy", "Lab")
    var downloadUrl:String=""
    private val fileViewModel: FileViewModel by viewModels()
    var password:String=""
    var strShopType=""
    var confirmPassword:String=""
    var request:RegisterRequest= RegisterRequest()
    private var accountType:String=MedWizConstants.Auth.ACCOUNT_SHOP
    private lateinit var binding: FragmentAddShopInfoBinding
    private val getFile=registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        val file: File = FileUtil.from(requireContext(), it)
        binding.btUpload.text = file.name
        val filePart= FileUtil.prepareFilePart("file",file,it,requireContext())
        fileViewModel.uploadFile(filePart)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddShopInfoBinding.bind(view)

        binding.imgBackAddInfo.setOnClickListener{

            findNavController().navigateUp()


        }

        binding.btUpload.setOnClickListener {
            getFile.launch("application/pdf")
        }

        fileViewModel.uploadFile.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    downloadUrl=it.data!!.downloadUrl
                    goToCreatePassword()

                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

        binding.btNextStep.setOnClickListener {
          goToCreatePassword()
        }

        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, shopList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerShop.adapter = aa

        binding.spinnerShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(position>0){
                    strShopType=shopList[position]
                }

            }

        }


    }

    private fun goToCreatePassword() {
        val name=binding.etLabName.text.toString()
        val mobile=binding.etPhoneNumber.text.toString()
        val email=binding.etMail.text.toString()
        val pinCode=binding.etPinCode.text.toString()
        val city=binding.etCity.text.toString()
        if(name.isNotEmpty()&&mobile.isNotEmpty()&&email.isNotEmpty()&&pinCode.isNotEmpty()&&downloadUrl.isNotEmpty()&&city.isNotEmpty()){

        val register= RegisterRequest()
        register.name=name
        register.mobile=mobile
        register.email=email
        register.pinCode=pinCode
        register.userType=accountType
        register.shopType=strShopType
        register.licencePath= downloadUrl
        register.city=city
        val bundle = Bundle()
        bundle.putParcelable(UtilConstants.request,register)
        findNavController().navigate(R.id.action_addLabInfoFragment_to_createPassword,bundle)
        }
    }
}