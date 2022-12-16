package com.medwiz.medwiz.patientsView.profile

import com.medwiz.medwiz.model.ProfileItemModel

interface ProfileItemListener {
    fun onClickItem(position: Int,itemObj: ProfileItemModel)

}