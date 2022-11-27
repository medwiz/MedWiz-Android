package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lab(
    var id:Long,
    var name: String,
    var addedDate:String,
    var licencePath: String,
    var reviewerName: String,
    var avatar: String,
    var rating:Int
): Parcelable{
    constructor() : this(0,"", "","","","",5)
}
