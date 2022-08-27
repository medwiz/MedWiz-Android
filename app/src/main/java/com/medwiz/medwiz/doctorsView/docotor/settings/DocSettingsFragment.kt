package com.medwiz.medwiz.ui.docotor.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocSettingLayoutBinding

class DocSettingsFragment:Fragment(R.layout.fragment_doc_setting_layout) {

    private lateinit var binding: FragmentDocSettingLayoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocSettingLayoutBinding.bind(view)
    }
}