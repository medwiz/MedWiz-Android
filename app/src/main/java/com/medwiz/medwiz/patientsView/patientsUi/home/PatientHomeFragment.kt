package com.medwiz.medwiz.patientsView.patientsUi.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentPatientHomeBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.booking.HealthTypeAdapter
import com.medwiz.medwiz.viewmodels.PatientViewModel
import com.medwiz.medwiz.patientsView.patientsUi.main.PatientMainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.lang.NullPointerException
import java.util.*

@AndroidEntryPoint
class PatientHomeFragment:Fragment(R.layout.fragment_patient_home), HomeScreenListener {
    private var adapter: DoctorsAdapter?=null
    private var topDoctorsAdapter: TopDoctorsAdapter?=null
    private var healthTypeAdapter:HealthTypeAdapter?=null
    private val viewModel: PatientViewModel by viewModels()
    private var userDetails: LoginResponse?=null
    private var allDoctors:ArrayList<DoctorResponse>?=null
    private lateinit var binding: FragmentPatientHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPatientHomeBinding.bind(view)


        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        val userType= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userType,"",false)
        val userId= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"0",false)
        val email= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.email,"",false)
        viewModel.getPatientByEmail(token,email)
        viewModel.getPatient.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
           when(it){
              is Resource.Loading->{
                  (activity as PatientMainActivity).showLoading()
              }
               is Resource.Success->{
                   (activity as PatientMainActivity).hideLoading()
                   this.userDetails=it.data!!
                   val name="Hi "+userDetails!!.firstname+" "+userDetails!!.lastname
                   binding.tvName.text= name
               }
               is Resource.Error->{
                   (activity as PatientMainActivity).hideLoading()
                   MedWizUtils.showErrorPopup(
                       requireActivity(),
                       it.message.toString())
               }
           }
       })

        createAdapters()

        getAllNearByDoctor(token)

        viewModel.getDoctor.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as PatientMainActivity).showLoading()
                }
                is Resource.Success->{
                    (activity as PatientMainActivity).showLoading()

                    val k=this.userDetails!!.pinCode
                    this.allDoctors=it.data!!
                    this.adapter!!.setData(allDoctors!!)
                    this.topDoctorsAdapter!!.setData(it.data)
                }
                is Resource.Error->{
                    (activity as PatientMainActivity).showLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })
        binding.btNearByViewAll.setOnClickListener{
            val bundle=Bundle()
            if(this.allDoctors!!.size>0){
            bundle.putParcelableArrayList(UtilConstants.doctor,this.allDoctors)
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_patientHomeFragment_to_viewAllDoctorsFragment,bundle)
            }
        }
        binding.btTopViewAll.setOnClickListener{
            val bundle=Bundle()
            bundle.putParcelableArrayList(UtilConstants.doctor,allDoctors)
            bundle.putBoolean(UtilConstants.nearbyDocs,false)
            findNavController().navigate(R.id.action_patientHomeFragment_to_viewAllDoctorsFragment,bundle)
        }
    }

    private fun getAllNearByDoctor(token: String) {
       viewModel.getAllNearByDoctors(token)


    }

    private fun createAdapters() {

        adapter = DoctorsAdapter(requireContext(),this)
        binding.rcvNearByDoc.adapter = adapter
        binding.rcvNearByDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcvNearByDoc.smoothScrollBy(50,100)

        healthTypeAdapter = HealthTypeAdapter(requireActivity(),this)
        binding.rcvHealthType.adapter = healthTypeAdapter
        binding.rcvHealthType.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        topDoctorsAdapter = TopDoctorsAdapter(requireContext(),this)
        binding.rcvAllDoc.adapter = adapter
        binding.rcvAllDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rcvAllDoc.smoothScrollBy(100,100)
    }


    override fun onClickConsult(position: Int, doctor: DoctorResponse) {
        try {
            val bundle=Bundle()
            bundle.putParcelable(UtilConstants.doctor,doctor)
            bundle.putBoolean(UtilConstants.nearbyDocs,true)
            findNavController().navigate(R.id.action_patientHomeFragment_to_doctorDetails,bundle)
        }catch (e:NullPointerException){
            findNavController().navigate(R.id.action_patientHomeFragment_to_doctorDetails)
        }

    }
}