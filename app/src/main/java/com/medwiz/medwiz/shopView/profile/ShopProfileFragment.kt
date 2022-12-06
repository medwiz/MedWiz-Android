package com.medwiz.medwiz.shopView.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentShopProfileBinding

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShopProfileFragment:Fragment(R.layout.fragment_shop_profile) {

//    private val consultationViewModel: ConsultationViewModel by viewModels()
    private lateinit var binding: FragmentShopProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopProfileBinding.bind(view)




    }
}