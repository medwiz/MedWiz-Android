package com.medwiz.medwiz.patientsView.booking

import com.medwiz.medwiz.model.ProfileItemModel

interface ProfileItemListener {
    fun onClickItem(position: Int,itemObj: ProfileItemModel)

}