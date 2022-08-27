package com.medwiz.medwiz.patientsView.patientModels
data class ReviewModel(
    val id: String,
    val rating: String,
    val heading: String,
    val reviewerName: String,
    val avatar: String,
    val reviewComment: String,
)
