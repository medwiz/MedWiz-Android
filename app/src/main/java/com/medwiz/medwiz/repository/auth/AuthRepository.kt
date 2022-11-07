package com.medwiz.medwiz.repository.auth

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.reponse.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: AuthApi):AuthRepoInterface  {

    override suspend fun login( jsonObject: JsonObject): Response<LoginResponse> {
        return api.login(jsonObject)
    }
}