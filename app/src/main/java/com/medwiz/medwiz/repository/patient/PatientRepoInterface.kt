package com.medwiz.medwiz.repository.patient

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.PatientResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.UserResponse
import retrofit2.Response

interface PatientRepoInterface {



    suspend fun getPatientById(token:String,id:String):Response<PatientResponse>
    suspend fun getNearByDoctors(token: String):Response<java.util.ArrayList<DoctorResponse>>

    suspend fun registerPatient(jsonObject: JsonObject):Response<CommonResponse>
}