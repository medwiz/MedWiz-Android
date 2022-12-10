package com.medwiz.medwiz.repository.auth

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response

interface AuthRepoInterface {


    suspend fun login( jsonObject: JsonObject): Response<LoginResponse>
    suspend fun loginShop( jsonObject: JsonObject): Response<ShopResponse>
    suspend fun register( jsonObject: JsonObject): Response<CommonResponse>
    suspend fun getUserById(token:String,id:String):Response<LoginResponse>
    suspend fun addMedicine(jsonObject: JsonObject):Response<CommonResponse>
    suspend fun addLabTest(jsonObject: JsonObject):Response<CommonResponse>
}