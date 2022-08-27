package com.medwiz.medwiz.patientsView.booking

import com.medwiz.medwiz.patientsView.patientModels.BookingDate

interface BookingListener {
    fun onClickDate(position: Int,dateObj: BookingDate)

}