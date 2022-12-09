package com.medwiz.medwiz.repository.consulat

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.ConsultationApi
import com.medwiz.medwiz.data.network.DoctorApi
import com.medwiz.medwiz.data.network.PatientApi
import com.medwiz.medwiz.data.network.ReviewApi
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.repository.patient.PatientRepoInterface
import com.medwiz.medwiz.util.UtilConstants
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class ConsultationRepository @Inject constructor(private val api: ConsultationApi): ConsultationRepoInterface {

    override suspend fun createNewConsultation(token: String,jsonObject: JsonObject): Response<Consultation> {
        return api.createNewConsultation(token,jsonObject)
    }

    override suspend fun getConsultationByDocId(token: String, id: Long,status:String): Response<ArrayList<Consultation>> {
        return api.getConsultationByDocId(token,id,status)
    }

    override suspend fun getConsultationByPatientId(
        token: String, id: Long, status: String): Response<ArrayList<Consultation>> {
        return api.getConsultationByPatientId(token,id,status)
    }

    override suspend fun updateConsultation(token: String, jsonObject: JsonObject, id: Long): Response<Consultation> {
        return api.updateConsultation(token,jsonObject,id)
    }


}