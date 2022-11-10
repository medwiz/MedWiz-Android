package com.medwiz.medwiz.repository.doctor

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.network.DoctorApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class DoctorRepository @Inject constructor(private val api: DoctorApi):DoctorRepoInterface  {

    override suspend fun getDoctorByEmail(token: String, email: String): Response<LoginResponse> {
        return api.getDoctorByEmail(token,email)
    }

    override suspend fun registerDoctor(jsonObject: JsonObject, email: String): Response<CommonResponse> {
        return api.registerDoctor(jsonObject,email)
    }


}