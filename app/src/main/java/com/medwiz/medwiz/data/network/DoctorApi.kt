package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList

interface DoctorApi {

    @GET(UtilConstants.getDoctorByEmail+"{email}")
    suspend fun getDoctorByEmail(@Header("Authorization") accessToken: String,
                            @Path("email")email:String):Response<LoginResponse>

    @POST(UtilConstants.doctorApi)
    suspend fun registerDoctor(@Body jsonObject: JsonObject):Response<CommonResponse>



}