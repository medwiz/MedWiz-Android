package com.medwiz.medwiz.ui.docotor.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDocHomeLayoutBinding
import com.medwiz.medwiz.models.Doctors
import com.medwiz.medwiz.models.Patient
import com.medwiz.medwiz.ui.patient.home.HomeScreenListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocHomeFragment:Fragment(R.layout.fragment_doc_home_layout), HomeScreenListener {
    private lateinit var binding:FragmentDocHomeLayoutBinding
    private var adapter: DocHomeAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocHomeLayoutBinding.bind(view)

        val d= Patient("1","Patient1",
            "","","","","","",20,
            "")
        val d1= Patient("2","Patient2",
            "","","","","","",24,
            "")
        val d2= Patient("3","Patient3",
            "","","","","","",25,
            "")
        val lis=ArrayList<Patient>()
        lis.add(d)
        lis.add(d1)
        lis.add(d2)

        adapter = DocHomeAdapter(requireActivity(),lis,this)
        binding.rcvPatients.adapter = adapter
        binding.rcvPatients.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickConsult(position: Int, doctor: Doctors) {
        val i=0
    }
}