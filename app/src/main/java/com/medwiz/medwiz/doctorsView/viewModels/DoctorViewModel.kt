package com.medwiz.medwiz.doctorsView.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.model.UserResponse
import com.medwiz.medwiz.repository.auth.AuthRepoInterface
import com.medwiz.medwiz.repository.doctor.DoctorRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(private val repository: DoctorRepoInterface, @ApplicationContext private val context: Context):ViewModel() {

    val getUser: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var getUserResponse: LoginResponse? = null



    public fun getDoctorByEmail(token:String,email:String)=viewModelScope.launch {
        callGetUserApi(token,email)
    }
    private suspend fun callGetUserApi(token:String,email:String){
        getUser.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getDoctorByEmail(token,email)
                getUser.postValue(handleGetUserResponse(response))
            }
            else
                getUser.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getUser.postValue(Resource.Error("Network Failure"))
                else -> getUser.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetUserResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.id>0) {
                    getUserResponse = resultResponse
                    return Resource.Success(getUserResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }


    public fun updateDoctor(token:String,email:String)=viewModelScope.launch {
        callGetUserApi(token,email)
    }
    private suspend fun callUpdateDoctorApi(token:String,email:String){
        getUser.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getDoctorByEmail(token,email)
                getUser.postValue(handleGetUserResponse(response))
            }
            else
                getUser.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getUser.postValue(Resource.Error("Network Failure"))
                else -> getUser.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleUpdateDoctorResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.id>0) {
                    getUserResponse = resultResponse
                    return Resource.Success(getUserResponse ?: resultResponse)
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