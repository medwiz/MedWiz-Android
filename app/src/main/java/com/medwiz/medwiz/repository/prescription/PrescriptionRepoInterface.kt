package com.medwiz.medwiz.repository.prescription
import com.google.gson.JsonObject
import com.medwiz.medwiz.model.Prescription
import retrofit2.Response

interface PrescriptionRepoInterface {



    suspend fun create(token:String,jsonObject: JsonObject):Response<Prescription>
}