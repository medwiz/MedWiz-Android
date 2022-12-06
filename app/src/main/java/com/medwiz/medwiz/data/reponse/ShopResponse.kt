package com.medwiz.medwiz.data.reponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopResponse(
    var id: Long=0,
    var token:String="",
    var name: String?="",
    var addedDate: String,
    var isActive: Boolean,
    var licencePath: String,

    var city: String,
    var state: String,
    var country: String,
    var pinCode: String,

    var email: String,
    var mobile: String,

    var credit: Double,
    var rating: Int,
    var shopType: String,
    var userType: String,
    var offer: String,
    var registeredAt: String,
    var lastLogin: String,
    var profileImageUrl: String,
): Parcelable {
    constructor():this(0,"","","",false,"","","","","","","",0.0,0,"",
    "","","","","")
}
