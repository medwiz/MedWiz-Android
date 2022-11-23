package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Consultation (
     var id:Long,
     var addedDate: String,
     var filePath: String,
     var patientMobile:String,
     var patientName:String,
     var age:Int,
     var patientGender:String,
     var fees:Int,
     var patientId: Long,
     var docId: Long,
     var laboratoryId: Long,
     var pharmaId: Long,
     var isCash:Boolean,
     var isActive:Boolean,
     var status: String,
     var transactionId: String,
     var consDate: String,
     var consTime: String,
     var prescriptionId:Long
): Parcelable {
    constructor():this(0,"","","","",0,"",0,0,0,0,0,false,false,
   "","","","" ,0)
}