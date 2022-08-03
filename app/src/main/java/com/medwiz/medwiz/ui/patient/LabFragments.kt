package com.medwiz.medwiz.ui.patient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentLabBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LabFragments:Fragment(R.layout.fragment_lab) {

    private lateinit var binding: FragmentLabBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLabBinding.bind(view)
    }
}