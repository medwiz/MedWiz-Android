package com.medwiz.medwiz.repository.doctor

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response

interface DoctorRepoInterface {



    suspend fun getDoctorByEmail(token:String,email:String):Response<LoginResponse>
    suspend fun registerDoctor(jsonObject: JsonObject,email: String):Response<CommonResponse>
}