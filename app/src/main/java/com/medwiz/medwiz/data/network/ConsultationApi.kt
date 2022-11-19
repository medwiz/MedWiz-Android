package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList

interface ConsultationApi {

    @POST(UtilConstants.consultationApi)
    suspend fun createNewConsultation(@Header("Authorization") accessToken: String,
                                      @Body jsonObject: JsonObject): Response<Consultation>

    @GET(UtilConstants.consultationApi+"doctor/{doctorId}")
    suspend fun getConsultationByDocId(@Header("Authorization") accessToken: String,
                                 @Path("doctorId")email:Long):Response<ArrayList<Consultation>>
}