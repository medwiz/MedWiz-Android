package com.medwiz.medwiz.data.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.PatientResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*

interface PatientApi {


    @POST(UtilConstants.patientApi+"register")
    suspend fun registerPatient(@Body jsonObject: JsonObject):Response<CommonResponse>


    @GET(UtilConstants.getPatientById+"{id}")
    suspend fun getPatientById(@Header("Authorization") accessToken: String,
                            @Path("id")id:String):Response<PatientResponse>

    @GET("doctor/")
    suspend fun getNearByDoctor(@Header("Authorization") accessToken: String):Response<java.util.ArrayList<DoctorResponse>>
}