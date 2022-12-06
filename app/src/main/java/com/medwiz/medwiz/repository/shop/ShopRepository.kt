package com.medwiz.medwiz.repository.shop

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.ShopApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class ShopRepository @Inject constructor(private val api:ShopApi):ShopRepoInterface {
    override suspend fun registerShop(jsonObject: JsonObject): Response<CommonResponse> {
        return api.registerShop(jsonObject)
    }

    override suspend fun getAllShops(token: String): Response<ArrayList<ShopResponse>> {
       return api.getallShops(token)
    }

    override suspend fun getShop(token: String, id: Long): Response<ShopResponse> {
        return api.getShop(token,id)
    }
}