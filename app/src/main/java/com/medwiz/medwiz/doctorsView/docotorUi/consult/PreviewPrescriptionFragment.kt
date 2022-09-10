package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewPrescriptionFragment:Fragment(R.layout.fragment_preview) {

    private lateinit var binding:FragmentPreviewBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentPreviewBinding.inflate(layoutInflater)

    }
}