package com.medwiz.medwiz.repository.patient

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response

interface PatientRepoInterface {



    suspend fun getPatientByEmail(token:String,email:String):Response<LoginResponse>
    suspend fun getNearByDoctors(token: String):Response<java.util.ArrayList<DoctorResponse>>
}