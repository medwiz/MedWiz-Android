package com.medwiz.medwiz.patientsView.booking

import com.medwiz.medwiz.patientsView.patientModels.BookingDate
import com.medwiz.medwiz.patientsView.patientModels.ProfileItemModel

interface ProfileItemListener {
    fun onClickItem(position: Int,itemObj:ProfileItemModel)

}