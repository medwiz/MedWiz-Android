package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.repository.consulat.ConsultationRepoInterface
import com.medwiz.medwiz.repository.patient.PatientRepoInterface
import com.medwiz.medwiz.repository.reviews.ReviewRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(private val repository: ConsultationRepoInterface, @ApplicationContext private val context: Context):ViewModel() {


    val consultation:MutableLiveData<Resource<Consultation>> = MutableLiveData()
    var consultationResponse:Consultation?=null

    val consultationList:MutableLiveData<Resource<ArrayList<Consultation>>> = MutableLiveData()
    var consultationResponseList:ArrayList<Consultation>?=null


    public fun createNewConsultation(token:String,jsonObject: JsonObject)=viewModelScope.launch {

        callConsultationApi(token,jsonObject)

    }
    private suspend fun callConsultationApi(token:String,jsonObject: JsonObject){
        consultation.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.createNewConsultation(token,jsonObject)
                consultation.postValue(handleConsultationResponse(response))
            }
            else
                consultation.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> consultation.postValue(Resource.Error("Network Failure"))
                else -> consultation.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handleConsultationResponse(response: Response<Consultation>): Resource<Consultation> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.docId>0) {
                    consultationResponse = resultResponse
                    return Resource.Success(consultationResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }


    public fun getConsultationByDocId(token:String,id:Long)=viewModelScope.launch {

        callGetConsultationApi(token,id)

    }
    private suspend fun callGetConsultationApi(token:String,id:Long){
        consultationList.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getConsultationByDocId(token,id)
                consultationList.postValue(handleConsultationByDocResponse(response))
            }
            else
                consultationList.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> consultationList.postValue(Resource.Error("Network Failure"))
                else -> consultationList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleConsultationByDocResponse(response: Response<ArrayList<Consultation>>): Resource<ArrayList<Consultation>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.isNotEmpty()) {
                    consultationResponseList = resultResponse
                    return Resource.Success(consultationResponseList ?: resultResponse)
                }
            }
        }
        else if(response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

}