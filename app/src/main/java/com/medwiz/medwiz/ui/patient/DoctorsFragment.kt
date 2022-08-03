package com.medwiz.medwiz.ui.patient

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorsBinding
import com.medwiz.medwiz.models.Doctors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsFragment:Fragment(R.layout.fragment_doctors),HomeScreenListener {
    private var adapter: DoctorsAdapter?=null
    private lateinit var binding: FragmentDoctorsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorsBinding.bind(view)

        val d=Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d1=Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val d2=Doctors("1","Dr Sandipak",
            "Ray","","","","","","Dharnamanagar",
            "","")
        val lis=ArrayList<Doctors>()
        lis.add(d)
        lis.add(d1)
        lis.add(d2)

        adapter = DoctorsAdapter(requireActivity(),lis,this)
        binding.rcvDoctors.adapter = adapter
        binding.rcvDoctors.layoutManager = LinearLayoutManager(requireActivity())
       // binding.rcvDoctors.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onClickConsult(position: Int, doctor: Doctors) {
        findNavController().navigate(R.id.action_doctorsFragment_to_doctorBookingFragment)
    }
}