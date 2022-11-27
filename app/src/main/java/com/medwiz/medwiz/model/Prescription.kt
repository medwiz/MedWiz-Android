package com.medwiz.medwiz.model

import android.os.Parcelable
import com.medwiz.medwiz.doctorsView.model.Medication
import kotlinx.parcelize.Parcelize
import java.util.ArrayList
@Parcelize
data class Prescription(
    val active: Boolean,
    val addedDate: String,
    val docId: Int,
    val docName: String,
    val id: Long,
    val specialization:String,
    val age:Int,
    val gender:String,
    val weight:Int,
    val medicationLabs: ArrayList<MedicationLab>,
    val medications: ArrayList<Medication>,
    val patientId: Int,
    val patientName: String,
    val updateDate: String
): Parcelable
@Parcelize
data class MedicationLab(
    val id: Int,
    val name: String
): Parcelable
@Parcelize
data class Medication(
    var afternoonDose: Int,
    var id: Int,
    var morningDose: Int,
    var name: String,
    var nightDose: Int,
    var noOfDays: Int
): Parcelable{
    constructor() : this(0, 0,

        0, "",0,0
    )
}
