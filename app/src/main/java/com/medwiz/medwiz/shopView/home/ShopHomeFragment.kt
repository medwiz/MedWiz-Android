package com.medwiz.medwiz.shopView.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.databinding.FragmentShopHomeBinding
import com.medwiz.medwiz.shopView.ShopActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ShopViewModel

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShopHomeFragment:Fragment(R.layout.fragment_shop_home) {

    private val shopViewModel: ShopViewModel by viewModels()
    private var shopResponse:ShopResponse?=null
    private lateinit var binding: FragmentShopHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopHomeBinding.bind(view)
        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        val userType= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userType,"",false)
        val userId= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"0",false)
        shopViewModel.getShopById(token,userId.toLong())


        shopViewModel.getShopById.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as ShopActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as ShopActivity).hideLoading()
                   if(it.data!=null){
                       (activity as ShopActivity).setShopResponse(it.data)
                       shopResponse=(activity as ShopActivity).getShopResponse()
                       binding.tvShopTitle.text=shopResponse!!.name
                   }
                }
                is Resource.Error->{
                    (activity as ShopActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }else{
                        MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                    }
                }
            }
        })


    }
}