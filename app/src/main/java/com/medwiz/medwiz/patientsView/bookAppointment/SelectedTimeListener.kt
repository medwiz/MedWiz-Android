package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import com.medwiz.medwiz.model.CustomDateEntity
import com.medwiz.medwiz.model.CustomTimeEntity

interface SelectedTimeListener {

    fun onSelectedTime(position:Int,obj: CustomTimeEntity)
}