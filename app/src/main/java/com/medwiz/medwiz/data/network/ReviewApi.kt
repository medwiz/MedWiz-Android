package com.medwiz.medwiz.data.network

import com.google.gson.JsonObject
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList

interface ReviewApi {

    @GET(UtilConstants.review+"{email}")
    suspend fun getAllReviews(@Header("Authorization") accessToken: String,
                              @Path("email")email:String): Response<ArrayList<Review>>
}