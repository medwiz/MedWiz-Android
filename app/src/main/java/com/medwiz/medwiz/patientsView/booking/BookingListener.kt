package com.medwiz.medwiz.patientsView.booking

import com.medwiz.medwiz.model.BookingDate

interface BookingListener {
    fun onClickDate(position: Int,dateObj: BookingDate)

}