package com.medwiz.medwiz.data.reponse

data class LoginResponse(
    val address: String,
    val credit: String,
    val email: String,
    val mobile: String,
    val name: String,
    val pinCode: String,
    val token: String,
    val userId: Int,
    val userType: String
)