package com.medwiz.medwiz.repository.auth

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: AuthApi):AuthRepoInterface  {

    override suspend fun login( jsonObject: JsonObject): Response<LoginResponse> {
        return api.login(jsonObject)
    }

    override suspend fun loginShop( jsonObject: JsonObject): Response<ShopResponse> {
        return api.loginShop(jsonObject)
    }

    override suspend fun register(jsonObject: JsonObject): Response<CommonResponse> {
        return api.register(jsonObject)
    }

    override suspend fun getUserById(token:String,id: String): Response<LoginResponse> {
        return api.getUserById(token,id)
    }

    override suspend fun addMedicine(jsonObject: JsonObject): Response<CommonResponse> {
        return api.addMedicine(jsonObject)
    }
}