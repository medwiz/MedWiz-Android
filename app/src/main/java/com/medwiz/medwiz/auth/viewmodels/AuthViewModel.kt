package com.medwiz.medwiz.auth.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.repository.auth.AuthRepoInterface
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
class AuthViewModel @Inject constructor(private val repository: AuthRepoInterface, @ApplicationContext private val context: Context):ViewModel() {

    val login: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var loginResponse: LoginResponse? = null

    fun login(username:String,password:String)=viewModelScope.launch {
        val requestObj = JsonObject()
        if (username.isEmpty()) {
            login.postValue(Resource.Error(context.getString(R.string.invalid_username)))
            return@launch
        }

        if (password.isEmpty()) {
            login.postValue(Resource.Error(context.getString(R.string.password_error)))
            return@launch
        }
        if (username.length < 3) {
            login.postValue(Resource.Error(context.getString(R.string.invalid_username)))
            return@launch
        }
        if (password.length < 3) {
            login.postValue(Resource.Error(context.getString(R.string.paasword_length_error)))
            return@launch
        }

        requestObj.addProperty(UtilConstants.USERNAME, username)
        requestObj.addProperty(UtilConstants.PASSWORD, password)
        callLoginApi(requestObj)
    }

    private suspend fun callLoginApi(jsonObject: JsonObject){
        login.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.login(jsonObject)
                login.postValue(handleLoginResponse(response))
            }
            else
                login.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> login.postValue(Resource.Error("Network Failure"))
                else -> login.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.token.isNotEmpty()) {
                    loginResponse = resultResponse
                    return Resource.Success(loginResponse ?: resultResponse)
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