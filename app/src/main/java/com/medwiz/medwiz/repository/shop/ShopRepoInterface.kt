package com.medwiz.medwiz.repository.shop

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import retrofit2.Response
import java.util.ArrayList

interface ShopRepoInterface {



    suspend fun registerShop(jsonObject: JsonObject):Response<CommonResponse>
    suspend fun getAllShops(token:String):Response<ArrayList<ShopResponse>>
    suspend fun getShop(token:String,id:Long):Response<ShopResponse>

}