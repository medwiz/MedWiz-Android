package com.medwiz.medwiz.patientsView.patientsUi.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment:Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }
}