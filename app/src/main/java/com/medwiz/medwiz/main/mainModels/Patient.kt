package com.medwiz.medwiz.main.mainModels

import androidx.annotation.NonNull

data class Patient(
    val id: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
    val state: String,
    val address: String,
    val pinCode: String,
    val city: String,
    val age:Int,
    val mobile: String
  //  val prescriptions:ArrayList<String>
)
