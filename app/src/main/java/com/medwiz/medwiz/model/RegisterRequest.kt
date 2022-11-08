package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    var address: String,
    var age: String,
    var credit: String,
    var email: String,
    var firstname: String,
    var lastname: String,
    var mobile: String,
    var password: String,
    var pinCode: String,
    var userType: String
): Parcelable {
    constructor() : this("", "",
        "", "", "",
        "", "", "",
        "", ""
    )
}