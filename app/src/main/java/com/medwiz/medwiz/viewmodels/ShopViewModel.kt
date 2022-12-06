package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.repository.shop.ShopRepoInterface
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
class ShopViewModel @Inject constructor(private val repository:ShopRepoInterface,@ApplicationContext private val context: Context):ViewModel() {

    val getShops: MutableLiveData<Resource<ArrayList<ShopResponse>>> = MutableLiveData()
    var getShopsResponse: ArrayList<ShopResponse>? = null

    val getShopById: MutableLiveData<Resource<ShopResponse>> = MutableLiveData()
    var getShopByIdResponse: ShopResponse? = null

    val registerShop: MutableLiveData<Resource<CommonResponse>> = MutableLiveData()
    var registerShopResponse: CommonResponse? = null


    fun registerShop(jsonObject: JsonObject)=viewModelScope.launch{
        callRegisterApi(jsonObject)
    }

    private suspend fun callRegisterApi(jsonObject: JsonObject){
        registerShop.postValue(Resource.Loading())
        try {
            if(NetworkUtils.isInternetAvailable(context)){
              val response=repository.registerShop(jsonObject)
                registerShop.postValue(handleRegisterShopResponse(response))
            }
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> registerShop.postValue(Resource.Error("Network Failure"))
                else -> registerShop.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleRegisterShopResponse(response: Response<CommonResponse>): Resource<CommonResponse>? {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                registerShopResponse=resultResponse
                return Resource.Success(registerShopResponse?:resultResponse)
            }
        }else if(response.code()==500||response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    fun getAllShops(token:String)=viewModelScope.launch {
        callGetAllShopsApi(token)
    }

    private suspend fun callGetAllShopsApi(token: String){
        getShops.postValue(Resource.Loading())
        try {
            if(NetworkUtils.isInternetAvailable(context)){
                val response=repository.getAllShops(token)
                getShops.postValue(handleGetAllShopResponse(response))
            }

        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getShops.postValue(Resource.Error("Network Failure"))
                else -> getShops.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetAllShopResponse(response: Response<ArrayList<ShopResponse>>): Resource<ArrayList<ShopResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.size>0) {
                    getShopsResponse = resultResponse
                    return Resource.Success(getShopsResponse ?: resultResponse)
                }
            }
        }else if(response.code()==500||response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())

    }

    //
    fun getShopById(token:String,id:Long)=viewModelScope.launch {
        callGetShopByIdApi(token,id)
    }

    private suspend fun callGetShopByIdApi(token: String,id:Long){
        getShopById.postValue(Resource.Loading())
        try {
            if(NetworkUtils.isInternetAvailable(context)){
                val response=repository.getShop(token,id)
                getShopById.postValue(handleGetShopByIdResponse(response))
            }

        }
        catch (ex: Exception){
            when(ex){
                is IOException -> getShopById.postValue(Resource.Error("Network Failure"))
                else -> getShopById.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetShopByIdResponse(response: Response<ShopResponse>): Resource<ShopResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                    getShopByIdResponse = resultResponse
                    return Resource.Success(getShopByIdResponse ?: resultResponse)

            }
        }else if(response.code()==500||response.code()==401){
            return Resource.Error(UtilConstants.unauthorized)
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())

    }


}