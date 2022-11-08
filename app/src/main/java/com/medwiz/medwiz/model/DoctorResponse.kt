package com.medwiz.medwiz.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorResponse(
    var activated: Boolean,
    var address: String,
    var age: Int,
    var credit: String,
    var email: String,
    var experience: String,
    var firstname: String,
    var id: Int,
    var lastname: String,
    var licencePath: String,
    var mobile: String,
    var pinCode: String,
    var specialization: String
): Parcelable