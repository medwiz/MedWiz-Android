package com.medwiz.medwiz.doctorsView.model

data class Medicine(
    var name:String,
    var noOfDays:Int,
    var isMorning:Boolean,
    var isAfterNoon:Boolean,
    var isNight:Boolean
)
