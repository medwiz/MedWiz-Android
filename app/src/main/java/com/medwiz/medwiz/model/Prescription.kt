package com.medwiz.medwiz.model

import com.medwiz.medwiz.doctorsView.model.Medication
import java.util.ArrayList

data class Prescription(
    var id: Long,
    var addedDate: String,
    var isActive: Boolean,
    var updateDate: String,
    var patientId: Long,
    var docId: Long,
    var docName: String,
    var patientName:String,
    var medicineList:ArrayList<Medication>
)
