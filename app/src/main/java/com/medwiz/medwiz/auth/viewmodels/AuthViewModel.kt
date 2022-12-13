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
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.model.RegisterRequest
import com.medwiz.medwiz.model.UserResponse
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

    val loginShop: MutableLiveData<Resource<ShopResponse>> = MutableLiveData()
    var loginShopResponse: ShopResponse? = null

    val register: MutableLiveData<Resource<CommonResponse>> = MutableLiveData()
    var registerResponse: CommonResponse? = null

    val getUser: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var getUserResponse: LoginResponse? = null

    val addMedicine: MutableLiveData<Resource<CommonResponse>> = MutableLiveData()
    var addMedicineResponse: CommonResponse? = null


    //signUp

    fun signUp(request: RegisterRequest)=viewModelScope.launch {
        val requestObj = JsonObject()


        requestObj.addProperty(UtilConstants.PASSWORD, request.password)
        requestObj.addProperty(UtilConstants.email, request.email)
        requestObj.addProperty(UtilConstants.userType, request.userType)
        callRegisterApi(requestObj)
    }

    private suspend fun callRegisterApi(jsonObject: JsonObject){
        register.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.register(jsonObject)
                register.postValue(handleRegisterResponse(response))
            }
            else
                register.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> register.postValue(Resource.Error("Network Failure"))
                else -> register.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handleRegisterResponse(response: Response<CommonResponse>): Resource<CommonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.success) {
                    registerResponse = resultResponse
                    return Resource.Success(resultResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

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
        else if(response.code()==500||response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    public fun getUserById(token:String,id:String)=viewModelScope.launch {
        callGetUserApi(token,id)
    }
    private suspend fun callGetUserApi(token:String,id:String){
        getUser.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getUserById(token,id)
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

    fun loginShop(username:String,password:String)=viewModelScope.launch {
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
        callShopLoginApi(requestObj)
    }

    private suspend fun callShopLoginApi(jsonObject: JsonObject){
        loginShop.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.loginShop(jsonObject)
                loginShop.postValue(handleLoginShopResponse(response))
            }
            else
                loginShop.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> loginShop.postValue(Resource.Error("Network Failure"))
                else -> loginShop.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handleLoginShopResponse(response: Response<ShopResponse>): Resource<ShopResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.token.isNotEmpty()) {
                    loginShopResponse = resultResponse
                    return Resource.Success(loginShopResponse ?: resultResponse)
                }
            }
        }
        else if(response.code()==500||response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    fun addMedicine(medicineObj: JsonObject,isUpdate:Boolean) =viewModelScope.launch{
           callMedicineApi(medicineObj,isUpdate)
    }

    private suspend fun callMedicineApi(medicineObj: JsonObject,isUpdate: Boolean) {
        addMedicine.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.addMedicine(medicineObj,isUpdate)
                addMedicine.postValue(handleAddMedicineResponse(response))
            }
            else
                addMedicine.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> addMedicine.postValue(Resource.Error("Network Failure"))
                else -> addMedicine.postValue(Resource.Error("Conversion Error"))
            }
        }

    }

    private fun handleAddMedicineResponse(response: Response<CommonResponse>): Resource<CommonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.success) {
                    addMedicineResponse = resultResponse
                    return Resource.Success(resultResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())

    }


    fun addLabTest(labTestObj: JsonObject) =viewModelScope.launch{
        callLabTestApi(labTestObj)
    }

    private suspend fun callLabTestApi(labTestObj: JsonObject) {
        addMedicine.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.addLabTest(labTestObj)
                addMedicine.postValue(handleLabTestResponse(response))
            }
            else
                addMedicine.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> addMedicine.postValue(Resource.Error("Network Failure"))
                else -> addMedicine.postValue(Resource.Error("Conversion Error"))
            }
        }

    }

    private fun handleLabTestResponse(response: Response<CommonResponse>): Resource<CommonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.success) {
                    addMedicineResponse = resultResponse
                    return Resource.Success(resultResponse ?: resultResponse)
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