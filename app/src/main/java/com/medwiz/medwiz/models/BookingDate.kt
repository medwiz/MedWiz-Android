package com.medwiz.medwiz.models

import androidx.annotation.NonNull

data class BookingDate(
    val id: Int,
    val bookingDate: String,
    val bookingDay: String,
    var isSelected:Boolean=false
)
