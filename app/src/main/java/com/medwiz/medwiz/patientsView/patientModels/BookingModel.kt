package com.medwiz.medwiz.patientsView.patientModels

import androidx.annotation.NonNull

data class BookingModel(
    val id: Int,
    val doctorName: String,
    val doctorImage: String,
    var specialization:String,
    val rating: String,
    val distance:String,
    val time:String
)
