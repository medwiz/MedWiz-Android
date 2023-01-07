package com.medwiz.medwiz.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentHomeBinding
import com.medwiz.medwiz.ui.main.MainActivity
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Specialization
import com.medwiz.medwiz.viewmodels.PatientViewModel
import com.medwiz.medwiz.patientsView.patientsUi.home.DoctorsAdapter
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientsUi.home.TopDoctorsAdapter
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment:Fragment(R.layout.fragment_home), HomeScreenListener {
    private var adapter: DoctorsAdapter?=null
    private var topDoctorsAdapter: TopDoctorsAdapter?=null
    private var healthTypeAdapter:SpecializationAdapter?=null
    private val viewModel: PatientViewModel by viewModels()
    private var allDoctors:ArrayList<DoctorResponse>?=null
    private val consultationViewModel: ConsultationViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        createHealthTypeAdapters()
        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)

        val id= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"",false)
        viewModel.getPatientById(token,id)

        createAdapters()

        getAllNearByDoctor(token)
        viewModel.getPatient.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }
                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    (activity as MainActivity).setUserDetails(it.data)
                    val name="Hi ${it.data!!.user.firstName} ${it.data.user.lastName}"
                     binding.tvName.text=name
                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })
        consultationViewModel.consultationList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }
                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                     if(it.data!=null&&it.data.isNotEmpty()){
                     setUpcomingBookingUi(it.data[0])
                     }
                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
//                    if(it.message!!.isNotEmpty()){
//                    MedWizUtils.showErrorPopup(
//                        requireActivity(),
//                        it.message.toString())
//                }
                }
            }
        })
        viewModel.getDoctor.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }
                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    if(it.data!!.isNotEmpty()){
                    this.allDoctors=it.data
                    this.adapter!!.setData(allDoctors!!)
                    this.topDoctorsAdapter!!.setData(it.data)
                    //val userId2=(activity as MainActivity).getUserDetails().id
                   // consultationViewModel.getConsultationByPatientId(token,userId,UtilConstants.STATUS_UPCOMING)
                    }
                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

        binding.tvNearByDoctorSeeAll.setOnClickListener{

        }
        binding.tvOrderNowSeeAll.setOnClickListener{

        }
    }

    private fun setUpcomingBookingUi(it: Consultation) {

    }


    private fun getAllNearByDoctor(token: String) {
      // viewModel.getAllNearByDoctors(token)


    }

    private fun createAdapters() {

//        adapter = DoctorsAdapter(requireContext(),this)
//        binding.rcvNearByDoc.adapter = adapter
//        binding.rcvNearByDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
//        binding.rcvNearByDoc.smoothScrollBy(50,100)
         createHealthTypeAdapters()

//        topDoctorsAdapter = TopDoctorsAdapter(requireContext(),this)
//        binding.rcvAllDoc.adapter = adapter
//        binding.rcvAllDoc.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
//        binding.rcvAllDoc.smoothScrollBy(100,100)
    }

    private fun createHealthTypeAdapters() {
        val list=ArrayList<Specialization>()
        val general=Specialization("General",requireContext().getDrawable(R.drawable.ic_profile))
        list.add(general)
        val dentist=Specialization("Dentist",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(dentist)
        val eye=Specialization("Eye",requireContext().getDrawable(R.drawable.ic_eye))
        list.add(eye)
        val cardiology=Specialization("Cardiology",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(cardiology)
        val pediatric=Specialization("Pediatric",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(pediatric)
        val nutrition=Specialization("Nutrition",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(nutrition)
        val gynae=Specialization("Gynae",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(gynae)
        val medicine=Specialization("Medicine",requireContext().getDrawable(R.drawable.ic_tick_green))
        list.add(medicine)

        healthTypeAdapter = SpecializationAdapter(requireActivity())
        binding.rcvSpecialization.adapter = healthTypeAdapter
        val screenSaverManager = GridLayoutManager(
            requireActivity(),
            4,
            RecyclerView.VERTICAL,
            false
        )
        binding.rcvSpecialization.layoutManager = screenSaverManager
        healthTypeAdapter!!.setData(list)
//
    }


    override fun onClickConsult(position: Int, doctor: DoctorResponse) {


    }
}