package com.medwiz.medwiz.doctorsView.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medicine(
    var id:Int,
    var name:String,
    var labTestName:String,
    var noOfDays:Int,
    var isMorning:Boolean,
    var isAfterNoon:Boolean,
    var isNight:Boolean
):Parcelable


