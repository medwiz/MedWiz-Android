package com.medwiz.medwiz.patientsView.patientViewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
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

    val getPatient: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var getPatientResponse: LoginResponse? = null

    val getDoctor:MutableLiveData<Resource<java.util.ArrayList<DoctorResponse>>> = MutableLiveData()
    var getDoctorResponse:java.util.ArrayList<DoctorResponse>?=null



    public fun getPatientByEmail(token:String,email:String)=viewModelScope.launch {
        callGetPatientApi(token,email)
    }
    private suspend fun callGetPatientApi(token:String,email:String){
        getPatient.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getPatientByEmail(token,email)
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

    private fun handleGetUserResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
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

                if(resultResponse.isNotEmpty()) {
                    getDoctorResponse = resultResponse
                   //return Resource.Error("message")
                    return Resource.Success(getDoctorResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }
}