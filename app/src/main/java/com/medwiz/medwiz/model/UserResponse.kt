package com.medwiz.medwiz.model

data class UserResponse(
    val address: String,
    val age: String,
    val credit: String,
    val email: String,
    val firstname: String,
    val id: Int,
    val lastname: String,
    val mobile: String,
    val pinCode: String,
    val roles: List<Role>,
    val userType: String
)

data class Role(
    val id: Int,
    val name: String
)