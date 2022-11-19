package com.medwiz.medwiz.repository.consulat

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response
import java.util.ArrayList

interface ConsultationRepoInterface {



    suspend fun createNewConsultation(token:String,jsonObject: JsonObject):Response<Consultation>
    suspend fun getConsultationByDocId(token:String,id: Long):Response<ArrayList<Consultation>>

}