package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.data.reponse.PatientResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.repository.patient.PatientRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val repository: PatientRepoInterface, @ApplicationContext private val context: Context):ViewModel() {

    val getPatient: MutableLiveData<Resource<PatientResponse>> = MutableLiveData()
    var getPatientResponse: PatientResponse? = null

    val getDoctor:MutableLiveData<Resource<java.util.ArrayList<DoctorResponse>>> = MutableLiveData()
    var getDoctorResponse:java.util.ArrayList<DoctorResponse>?=null

    val registerPatient: MutableLiveData<Resource<CommonResponse>> = MutableLiveData()
    var registerPatientResponse: CommonResponse? = null



    public fun getPatientById(token:String,id:String)=viewModelScope.launch {
        callGetPatientApi(token,id)
    }
    private suspend fun callGetPatientApi(token:String,id:String){
        getPatient.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getPatientById(token,id)
                getPatient.postValue(handleGetUserResponse(response))
            }
            else
                getPatient.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getPatient.postValue(Resource.Error("Network Failure"))
                else -> getPatient.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetUserResponse(response: Response<PatientResponse>): Resource<PatientResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.id>0) {
                    getPatientResponse = resultResponse
                    return Resource.Success(getPatientResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    fun getAllNearByDoctors(token: String)=viewModelScope.launch {
        callDoctorApi(token)
    }
    private suspend fun callDoctorApi(token: String){
        getDoctor.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getNearByDoctors(token)
                getDoctor.postValue(handleGetDoctorResponse(response))
            }
            else
                getDoctor.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getDoctor.postValue(Resource.Error("Network Failure"))
                else -> getDoctor.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetDoctorResponse(response: Response<java.util.ArrayList<DoctorResponse>>): Resource<java.util.ArrayList<DoctorResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                getDoctorResponse = resultResponse
                   //return Resource.Error("message")
                    return Resource.Success(getDoctorResponse ?: resultResponse)

            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }


    public fun registerPatient(jsonObj: JsonObject)=viewModelScope.launch {
        callRegisterPatientApi(jsonObj)
    }
    private suspend fun callRegisterPatientApi(jsonObj: JsonObject){
        registerPatient.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.registerPatient(jsonObj)
                registerPatient.postValue(handleRegisterPatientResponse(response))
            }
            else
                registerPatient.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> registerPatient.postValue(Resource.Error("Network Failure"))
                else -> registerPatient.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleRegisterPatientResponse(response: Response<CommonResponse>): Resource<CommonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                // if(resultResponse.id>0) {
                registerPatientResponse = resultResponse
                return Resource.Success(registerPatientResponse ?: resultResponse)
                // }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

}