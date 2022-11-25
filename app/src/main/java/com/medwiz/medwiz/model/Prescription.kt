package com.medwiz.medwiz.model

import com.medwiz.medwiz.doctorsView.model.Medication
import java.util.ArrayList

data class Prescription(
    val active: Boolean,
    val addedDate: String,
    val docId: Int,
    val docName: String,
    val id: Long,
    val medicationLabs: List<MedicationLab>,
    val medications: List<Medication>,
    val patientId: Int,
    val patientName: String,
    val updateDate: String
)

data class MedicationLab(
    val id: Int,
    val name: String
)

data class Medication(
    val afternoonDose: Int,
    val id: Int,
    val morningDose: Int,
    val name: String,
    val nightDose: Int,
    val noOfDays: Int
)