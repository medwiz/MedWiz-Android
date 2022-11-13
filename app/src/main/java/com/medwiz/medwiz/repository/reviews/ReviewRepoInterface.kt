package com.medwiz.medwiz.repository.reviews

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import java.util.ArrayList

interface ReviewRepoInterface {



    suspend fun getAllReviewByDoctor(token:String,email:String):Response<ArrayList<Review>>

}