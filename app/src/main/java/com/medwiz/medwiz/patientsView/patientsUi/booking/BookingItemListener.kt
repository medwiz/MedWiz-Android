package com.medwiz.medwiz.patientsView.patientsUi.booking


import com.medwiz.medwiz.patientsView.patientModels.BookingModel

interface BookingItemListener {
    fun onClickDate(position: Int,dateObj: BookingModel)

}