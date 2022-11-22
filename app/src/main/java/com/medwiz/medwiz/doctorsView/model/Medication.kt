package com.medwiz.medwiz.doctorsView.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medication(
    var id:Int,
    var name:String,
    var labTestName:String,
    var noOfDays:Int,
    var morningDose:Int,
    var afternoonDose:Int,
    var nightDose:Int
):Parcelable{
    constructor() : this(0, "",
        "", 0, 0,
        0,0)
}

