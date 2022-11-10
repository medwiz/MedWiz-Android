package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    var comments: String,
    var username: String,
    var rating:Int
): Parcelable{
    constructor() : this("", "",1)
}
