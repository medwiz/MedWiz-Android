package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    var comments: String,
    var docEmail:String,
    var heading: String,
    var reviewerName: String,
    var avatar: String,
    var addedDate:String,
    var rating:Int
): Parcelable{
    constructor() : this("", "","","","","",5)
}
