package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import com.medwiz.medwiz.model.CustomDateEntity

interface SelectedDateListener {

    fun onSelectedDate(position:Int,obj:CustomDateEntity)
}