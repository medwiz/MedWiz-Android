package com.medwiz.medwiz.patientsView.patientsUi.booking


import com.medwiz.medwiz.model.BookingModel

interface BookingItemListener {
    fun onClickDate(position: Int,dateObj: BookingModel)

}