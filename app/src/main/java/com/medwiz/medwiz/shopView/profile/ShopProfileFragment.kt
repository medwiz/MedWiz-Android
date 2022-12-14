package com.medwiz.medwiz.shopView.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentShopProfileBinding
import com.medwiz.medwiz.model.ProfileItemModel
import com.medwiz.medwiz.patientsView.profile.ProfileItemListener
import com.medwiz.medwiz.patientsView.profile.ProfileItemAdapter
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.UtilConstants

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShopProfileFragment:Fragment(R.layout.fragment_shop_profile), ProfileItemListener {

//    private val consultationViewModel: ConsultationViewModel by viewModels()
    private lateinit var binding: FragmentShopProfileBinding
    private var adapter: ProfileItemAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopProfileBinding.bind(view)
        val profileItem1= ProfileItemModel(UtilConstants.ITEM_PROFILE,"Profile",R.drawable.ic_profile_item)
        val profileItem2= ProfileItemModel(UtilConstants.ITEM_EDIT_PROFILE,"Edit Profile",R.drawable.ic_edit_profile_item)
        val profileItem3= ProfileItemModel(UtilConstants.ITEM_SETTING,"Setting",R.drawable.ic_setting_item)
        val logout= ProfileItemModel(UtilConstants.ITEM_LOGOUT,"Logout",R.drawable.ic_logout)
        val profileItem4= ProfileItemModel(UtilConstants.ITEM_TERMS,"Terms & Privacy Policy",R.drawable.ic_terms_item)
        val lis= ArrayList<ProfileItemModel>()
        lis.add(profileItem1)
        lis.add(profileItem2)
        lis.add(profileItem3)
        lis.add(logout)
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
            UtilConstants.ITEM_LOGOUT->{
                MedWizUtils.performLogout(requireContext(),requireActivity())
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