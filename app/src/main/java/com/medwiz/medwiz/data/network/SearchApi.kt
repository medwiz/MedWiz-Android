package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.FileResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.MedicineResponse
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.util.UtilConstants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList

interface SearchApi {

    @GET("medicine/search/{type}/{keyword}")
    suspend fun searchMedicine(@Path("type")type:String,
                               @Path("keyword")keyword:String): Response<ArrayList<MedicineResponse>>

    @GET("labtest/search/{keyword}")
    suspend fun searchLabTest(@Path("keyword")keyword:String): Response<ArrayList<MedicineResponse>>


}