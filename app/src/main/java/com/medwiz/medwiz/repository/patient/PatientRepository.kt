package com.medwiz.medwiz.repository.patient

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.network.DoctorApi
import com.medwiz.medwiz.data.network.PatientApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.PatientResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class PatientRepository @Inject constructor(private val api: PatientApi):PatientRepoInterface  {

    override suspend fun getPatientById(token: String, id: String): Response<PatientResponse> {
       return api.getPatientById(token,id)
    }

    override suspend fun getNearByDoctors(token: String): Response<java.util.ArrayList<DoctorResponse>> {
        return api.getNearByDoctor(token)
    }

    override suspend fun registerPatient(jsonObject: JsonObject): Response<CommonResponse> {
        return api.registerPatient(jsonObject)
    }


}