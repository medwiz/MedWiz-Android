package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkTimings(
    var day: String,
    var time: String,
): Parcelable{
    constructor() : this("", "")
}
