package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.repository.doctor.DoctorRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(private val repository: DoctorRepoInterface, @ApplicationContext private val context: Context):ViewModel() {

    val getDoctor: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var getDoctorResponse: LoginResponse? = null

    val registerDoctor: MutableLiveData<Resource<CommonResponse>> = MutableLiveData()
    var registerDoctorResponse: CommonResponse? = null




    public fun getDoctorByEmail(token:String,email:String)=viewModelScope.launch {
        callGetUserApi(token,email)
    }
    private suspend fun callGetUserApi(token:String,email:String){
        getDoctor.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getDoctorByEmail(token,email)
                getDoctor.postValue(handleGetUserResponse(response))
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

    private fun handleGetUserResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.id>0) {
                    getDoctorResponse = resultResponse
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


    public fun updateDoctor(jsonObj:JsonObject,email:String)=viewModelScope.launch {
        callRegisterDoctorApi(jsonObj,email)
    }
    private suspend fun callRegisterDoctorApi(jsonObj:JsonObject,email:String){
        registerDoctor.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.registerDoctor(jsonObj,email)
                registerDoctor.postValue(handleRegisterDoctorResponse(response))
            }
            else
                registerDoctor.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> registerDoctor.postValue(Resource.Error("Network Failure"))
                else -> registerDoctor.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleRegisterDoctorResponse(response: Response<CommonResponse>): Resource<CommonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
               // if(resultResponse.id>0) {
                    registerDoctorResponse = resultResponse
                    return Resource.Success(registerDoctorResponse ?: resultResponse)
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