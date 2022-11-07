package com.medwiz.medwiz.repository.auth

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.LoginResponse
import retrofit2.Response

interface AuthRepoInterface {


    suspend fun login( jsonObject: JsonObject): Response<LoginResponse>
}