package com.medwiz.medwiz.model

data class CustomDateEntity(
    var day:String,
    var date:String,
    var actualDate:String,
    var isSelected:Boolean
){
    constructor() : this("", "","",false)
}
