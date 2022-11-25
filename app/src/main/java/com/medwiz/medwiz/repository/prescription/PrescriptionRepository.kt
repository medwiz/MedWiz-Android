package com.medwiz.medwiz.repository.prescription

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.network.DoctorApi
import com.medwiz.medwiz.data.network.PatientApi
import com.medwiz.medwiz.data.network.PrescriptionApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class PrescriptionRepository @Inject constructor(private val api: PrescriptionApi):PrescriptionRepoInterface  {
    override suspend fun create(token: String, jsonObject: JsonObject): Response<Prescription> {
        return api.create(token,jsonObject)
    }

    override suspend fun getPrescriptionList(token: String, id: Long): Response<ArrayList<Prescription>> {
        return api.getPrescriptionList(token,id.toString())
    }


}