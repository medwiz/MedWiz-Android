package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Consultation (
     var addedDate: String,
     var filePath: String,
     var fees:Int,
     var uId: Long,
     var dId: Long,
     var lId: Long,
     var pId: Long,
     var isCash:Boolean,
     var isActive:Boolean,
     var status: String,
     var transactionId: String,
     var cDate: String,
     var cTime: String
): Parcelable {
    constructor():this("","",0,0,0,0,0,false,false,"",
   "","","" )
}