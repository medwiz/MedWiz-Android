package com.medwiz.medwiz.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.shopView.ShopActivity
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SelectAccountFragmentBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizConstants
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectAccountFragment:Fragment(R.layout.select_account_fragment) {
    private lateinit var binding:SelectAccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SelectAccountFragmentBinding.bind(view)

       val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
       val userType= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userType,"",false)
        val userId= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"0",false)

        binding.tvMed.setOnClickListener {
            findNavController().navigate(R.id.action_selectAccountFragment_to_addMedicineInfoFragment)
        }
        binding.tvLabTest.setOnClickListener {
            findNavController().navigate(R.id.action_selectAccountFragment_to_addLabTestInfoFragment)
        }
        binding.tvUpdate.setOnClickListener{

            findNavController().navigate(R.id.action_selectAccountFragment_to_updateMedicineFragment)


        }

        binding.rlDoctor.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_DOCTOR)
            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)


        }

        binding.rlPatient.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_PATIENT)
            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)


        }
        binding.rlLab.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(MedWizConstants.Auth.ACCOUNT_TYPE,MedWizConstants.Auth.ACCOUNT_SHOP)
            findNavController().navigate(R.id.action_selectAccountFragment_to_loginFragment,bundle)


        }




        if(token.isNotEmpty()&&userId.isNotEmpty()&&userType.isNotEmpty()&&(userType==MedWizConstants.Auth.ACCOUNT_SHOP||
                    userType==MedWizConstants.Auth.ACCOUNT_DOCTOR||userType==MedWizConstants.Auth.ACCOUNT_PATIENT)){
            when(userType){
                MedWizConstants.Auth.ACCOUNT_DOCTOR->{
                    val intent = Intent (requireActivity(), DoctorsActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()

                }
                MedWizConstants.Auth.ACCOUNT_PATIENT->{
                    val intent = Intent (requireActivity(), PatientMainActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()

                }
                MedWizConstants.Auth.ACCOUNT_SHOP->{
                    val intent = Intent (requireActivity(), ShopActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()

                }
            }

        }


    }


}