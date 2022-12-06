package com.medwiz.medwiz.shopView.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentShopHistoryBinding

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShopHistoryFragment:Fragment(R.layout.fragment_shop_history) {

//    private val consultationViewModel: ConsultationViewModel by viewModels()
    private lateinit var binding: FragmentShopHistoryBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopHistoryBinding.bind(view)




    }
}