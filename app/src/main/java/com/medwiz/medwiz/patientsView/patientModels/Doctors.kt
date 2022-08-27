package com.medwiz.medwiz.patientsView.patientModels

import androidx.annotation.NonNull

data class Doctors(
    val id: String,
    val firstName: String,
    val lastName: String,
    val specialization: String,
    val mail: String,
    val state: String,
    val address: String,
    val pinCode: String,
    val city: String,
    val experience: String,
    val mobile: String
  //  val prescriptions:ArrayList<String>
)
