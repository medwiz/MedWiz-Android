package com.medwiz.medwiz.patientsView.patientsUi.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentProfileBinding
import com.medwiz.medwiz.patientsView.booking.ProfileItemListener
import com.medwiz.medwiz.patientsView.patientModels.ProfileItemModel
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class ProfileFragments:Fragment(R.layout.fragment_profile),ProfileItemListener {
    private var adapter: ProfileItemAdapter?=null
    private lateinit var binding: FragmentProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        val profileItem1=ProfileItemModel(UtilConstants.ITEM_PROFILE,"Profile",R.drawable.ic_profile_item)
        val profileItem2=ProfileItemModel(UtilConstants.ITEM_EDIT_PROFILE,"Edit Profile",R.drawable.ic_edit_profile_item)
        val profileItem3=ProfileItemModel(UtilConstants.ITEM_SETTING,"Setting",R.drawable.ic_setting_item)
        val profileItem4=ProfileItemModel(UtilConstants.ITEM_TERMS,"Terms & Privacy Policy",R.drawable.ic_terms_item)
        val lis= ArrayList<ProfileItemModel>()
        lis.add(profileItem1)
        lis.add(profileItem2)
        lis.add(profileItem3)
        lis.add(profileItem4)
        adapter = ProfileItemAdapter(requireActivity(),lis,this)
        binding.rcvProfileList.adapter = adapter
        binding.rcvProfileList.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onClickItem(position: Int, itemObj: ProfileItemModel) {
        when(itemObj.id){
            UtilConstants.ITEM_PROFILE->{
               val profile=1
            }
            UtilConstants.ITEM_EDIT_PROFILE->{
                val editProfile=1
            }
            UtilConstants.ITEM_SETTING->{
                val settings=1
            }
            UtilConstants.ITEM_TERMS->{
                val terms=1
            }
        }
    }
}