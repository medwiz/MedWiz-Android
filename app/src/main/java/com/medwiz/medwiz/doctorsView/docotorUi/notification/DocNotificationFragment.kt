package com.medwiz.medwiz.doctorsView.docotorUi.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocNotificationBinding
import com.medwiz.medwiz.databinding.FragmentDocProfileBinding

class DocNotificationFragment:Fragment(R.layout.fragment_doc_notification) {
    private lateinit var binding: FragmentDocNotificationBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocNotificationBinding.bind(view)
    }
}