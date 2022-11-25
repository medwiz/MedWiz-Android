package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.repository.prescription.PrescriptionRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PrescriptionViewModel @Inject constructor(private val repository: PrescriptionRepoInterface, @ApplicationContext private val context: Context):ViewModel() {


    val prescription:MutableLiveData<Resource<Prescription>> = MutableLiveData()
    var prescriptionResponse:Prescription?=null

    val prescriptionList:MutableLiveData<Resource<java.util.ArrayList<Prescription>>> = MutableLiveData()
    var prescriptionListResponse:ArrayList<Prescription>? = null



    public fun createPrescription(token:String,jsonObject: JsonObject)=viewModelScope.launch {

        callPrescriptionApi(token,jsonObject)

    }
    private suspend fun callPrescriptionApi(token:String,jsonObject: JsonObject){
        prescription.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.create(token,jsonObject)
                prescription.postValue(handlePrescriptionResponse(response))
            }
            else
                prescription.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> prescription.postValue(Resource.Error("Network Failure"))
                else -> prescription.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handlePrescriptionResponse(response: Response<Prescription>): Resource<Prescription> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.id>0) {
                    prescriptionResponse = resultResponse
                    return Resource.Success(prescriptionResponse ?: resultResponse)
                }
            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    fun getPrescriptionList(token: String, id: Long) = viewModelScope.launch{
          callPrescriptionListApi(token,id)
    }

    private suspend fun callPrescriptionListApi(token: String, id: Long) {
        prescriptionList.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getPrescriptionList(token,id)
                prescriptionList.postValue(handlePrescriptionListResponse(response))
            }
            else
                prescriptionList.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> prescriptionList.postValue(Resource.Error("Network Failure"))
                else -> prescriptionList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handlePrescriptionListResponse(response: Response<ArrayList<Prescription>>): Resource<ArrayList<Prescription>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.isNotEmpty()) {
                    prescriptionListResponse = resultResponse
                    return Resource.Success(prescriptionListResponse ?: resultResponse)
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