package com.medwiz.medwiz.data.network

import com.google.gson.JsonArray
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*

interface PatientApi {

    @GET(UtilConstants.getPatientByEmail+"{email}")
    suspend fun getPatientByEmail(@Header("Authorization") accessToken: String,
                            @Path("email")email:String):Response<LoginResponse>

    @GET(UtilConstants.doctor)
    suspend fun getNearByDoctor(@Header("Authorization") accessToken: String):Response<java.util.ArrayList<DoctorResponse>>
}