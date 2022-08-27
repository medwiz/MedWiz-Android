package com.medwiz.medwiz.doctorsView.docotorUi.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocHomeLayoutBinding
import com.medwiz.medwiz.doctorsView.docotorUi.CanceledFragment
import com.medwiz.medwiz.doctorsView.docotorUi.CompletedFragment
import com.medwiz.medwiz.doctorsView.docotorUi.UpcomingFragment
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.ui.docotor.home.DocHomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocHomeFragment:Fragment(R.layout.fragment_doc_home_layout), HomeScreenListener {
    private val tabNameArray = arrayOf(
        "Upcoming",
        "Completed",
        "Cancelled"
    )
    private lateinit var binding:FragmentDocHomeLayoutBinding
    private var adapter: DocHomeAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocHomeLayoutBinding.bind(view)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNameArray[position]
        }.attach()

    }

    override fun onClickConsult(position: Int, doctor: Doctors) {
        val i=0
    }




}