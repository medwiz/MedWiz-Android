package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList

interface ShopApi {

    @GET(UtilConstants.getAllShopApi)
    suspend fun getallShops(@Header("Authorization") accessToken: String):Response<ArrayList<ShopResponse>>

    @POST(UtilConstants.shopRegisterApi)
    suspend fun registerShop(@Body jsonObject: JsonObject):Response<CommonResponse>

    @GET(UtilConstants.getShopApi+"{id}")
    suspend fun getShop(@Header("Authorization") accessToken: String,
                        @Path("id") id:Long):Response<ShopResponse>





}