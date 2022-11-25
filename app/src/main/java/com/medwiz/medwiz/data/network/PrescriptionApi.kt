package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*

interface PrescriptionApi {

    @POST(UtilConstants.prescriptionApi)
    suspend fun create(@Header("Authorization") accessToken: String,
                       @Body jsonObject: JsonObject): Response<Prescription>


    @GET(UtilConstants.prescriptionApi+"patient/{patientId}")
    suspend fun getPrescriptionList(@Header("Authorization") accessToken: String,
                                    @Path("patientId")userId:String): Response<ArrayList<Prescription>>




}