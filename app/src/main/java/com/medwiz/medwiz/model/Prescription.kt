package com.medwiz.medwiz.model

import com.medwiz.medwiz.doctorsView.model.Medicine
import java.util.ArrayList

data class Prescription(
    var id: Long,
    var addedDate: String,
    var isActive: Boolean,
    var updateDate: String,
    var patientId: Long,
    var docId: Long,
    var docName: String,
    var medicineList: ArrayList<Medicine>
)
