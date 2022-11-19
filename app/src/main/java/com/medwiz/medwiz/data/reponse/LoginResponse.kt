package com.medwiz.medwiz.data.reponse

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    var address: String,
    var credit: String,
    var email: String,
    var mobile: String,
    var firstname: String,
    var lastname:String,
    var pinCode: String,
    var token: String,
    var id: Long,
    var userType: String,
    var gender:String
):Parcelable{
    constructor() : this("", "",
        "", "", "",
        "", "", "",
        0, "",""
    )
}