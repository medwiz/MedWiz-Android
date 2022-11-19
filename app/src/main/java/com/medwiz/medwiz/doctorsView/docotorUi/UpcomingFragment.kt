package com.medwiz.medwiz.doctorsView.docotorUi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentUpcomingBinding
import com.medwiz.medwiz.doctorsView.docotorUi.consult.PrescriptionMainActivity
import com.medwiz.medwiz.doctorsView.docotorUi.home.PatientAdapter
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment:Fragment(R.layout.fragment_upcoming), ConsultationListener {
    private lateinit var binding:FragmentUpcomingBinding
    private var adapter: PatientAdapter?=null
    private var userDetails: LoginResponse?=null
    private val consultationViewModel: ConsultationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingBinding.bind(view)
        crateAdapter()

        consultationViewModel.consultationList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as DoctorsActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as DoctorsActivity).hideLoading()
                     adapter!!.setData(it.data!!)


                }
                is Resource.Error->{
                    (activity as DoctorsActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }

                }
            }
        })

    }



    private fun crateAdapter() {
        adapter = PatientAdapter(requireActivity(),UtilConstants.TYPE_UPCOMING,this)
        binding.rcvPatient.adapter = adapter
        binding.rcvPatient.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        val id=MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.docId,"",false)
        val token=MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        if(id.isNotEmpty()){
          consultationViewModel.getConsultationByDocId(token,id.toLong())
        }
    }

    override fun onClickConsult(position: Int, consultation: Consultation) {
        val intent = Intent (requireActivity(), PrescriptionMainActivity::class.java)
        intent.putExtra(UtilConstants.consultation,consultation)
        requireActivity().startActivity(intent)
        requireActivity().finish()


    }



}