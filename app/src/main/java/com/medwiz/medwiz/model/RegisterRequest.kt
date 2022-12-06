package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    var name:String,
    var shopType:String,
    var address: String,
    var age: String,
    var credit: String,
    var email: String,
    var firstname: String,
    var lastname: String,
    var mobile: String,
    var password: String,
    var pinCode: String,
    var userType: String,
    var gender: String,
    var isActivated:Boolean,
    var licencePath:String,
    var city:String,
): Parcelable {
    constructor() : this("","","", "",
        "", "", "",
        "", "", "",
        "", "","",false,"",""
    )
}