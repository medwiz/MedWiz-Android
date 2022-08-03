package com.medwiz.medwiz.ui.patient.booking

import com.medwiz.medwiz.models.BookingDate
import com.medwiz.medwiz.models.Doctors

interface BookingListener {
    fun onClickDate(position: Int,dateObj: BookingDate)

}