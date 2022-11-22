package com.medwiz.medwiz.doctorsView.docotorUi.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.FragmentDocHomeLayoutBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.viewmodels.DoctorViewModel
import com.medwiz.medwiz.ui.docotor.home.DocHomeAdapter
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocHomeFragment:Fragment(R.layout.fragment_doc_home_layout), ViewPagerListener {
    private val tabNameArray = arrayOf(
        "Upcoming",
        "Completed",
        "Cancelled"
    )
    private val viewModel: DoctorViewModel by viewModels()
    private lateinit var binding:FragmentDocHomeLayoutBinding
    private var userDetails: LoginResponse?=null
    private var adapter: DocHomeAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDocHomeLayoutBinding.bind(view)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle,this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNameArray[position]
        }.attach()
        val token= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.accessToken,"",false)
        val userType= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userType,"",false)
        val userId= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.userId,"0",false)
        val email= MedWizUtils.storeValueInPreference(requireContext(),
            UtilConstants.email,"",false)
        viewModel.getDoctorByEmail(token,email)
        viewModel.getDoctor.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    (activity as DoctorsActivity).showLoading()
                }

                is Resource.Success->{
                    this.userDetails=it.data!!
                    (activity as DoctorsActivity).setUserDetails(this.userDetails!!)
                    val name="Hi "+userDetails!!.firstname+" "+userDetails!!.lastname
                    MedWizUtils.storeValueInPreference(requireContext(),
                        UtilConstants.docId,userDetails!!.id.toString(),true)

                    binding.tvDoctorName.text= name

                }
                is Resource.Error->{
                    (activity as DoctorsActivity).hideLoading()
                    MedWizUtils.showErrorPopup(
                        requireActivity(),
                        it.message.toString())
                }
            }
        })

    }

    override fun onClickFragment(position: Int) {
        val i=position
    }


}