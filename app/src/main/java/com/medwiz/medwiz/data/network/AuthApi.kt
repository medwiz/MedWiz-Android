package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @POST(UtilConstants.login)
    suspend fun login(@Body jsonObject: JsonObject): Response<LoginResponse>

    @POST(UtilConstants.login+"/shop")
    suspend fun loginShop(@Body jsonObject: JsonObject): Response<ShopResponse>

    @POST(UtilConstants.register)
    suspend fun register(@Body jsonObject: JsonObject):Response<CommonResponse>

    @GET(UtilConstants.getUserById+"/"+"{userId}")
    suspend fun getUserById(@Header("Authorization") accessToken: String,
                            @Path("userId")userId:String):Response<LoginResponse>

    @POST(UtilConstants.addMedicine)
    suspend fun addMedicine(@Body jsonObject: JsonObject):Response<CommonResponse>

    @POST(UtilConstants.addLabTest)
    suspend fun addLabTest(@Body jsonObject: JsonObject):Response<CommonResponse>
}