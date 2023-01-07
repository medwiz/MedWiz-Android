package com.medwiz.medwiz.data.reponse

/**
 * @Author: Prithwiraj Nath
 * @Date:07/01/23
 */
data class PatientResponse(
    val age: Int,
    val createdAt: Long,
    val id: Int,
    val patientHistory: Any,
    val pinCode: Any,
    val profileImageUrl: Any,
    val updatedAt: Any,
    val user: User
)

data class User(
    val address: Address,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val lastName: String,
    val roles: List<Role>,
    val userPhoneNumber: String
)

data class Address(
    val address1: String,
    val address2: String,
    val city: String,
    val country: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val state: String,
    val zip: String
)

data class Role(
    val id: Int,
    val roleName: String
)