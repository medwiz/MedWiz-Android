package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.MedicineResponse
import com.medwiz.medwiz.repository.search.SearchRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList
import javax.inject.Inject

/**
 * @Author: Prithwiraj Nath
 * @Date:11/12/22
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository:SearchRepoInterface,@ApplicationContext private val context: Context):ViewModel() {

    val medicine: MutableLiveData<Resource<ArrayList<MedicineResponse>>> = MutableLiveData()
    var medicineResponse:java.util.ArrayList<MedicineResponse>?=null

    public fun searchMedicine(keywords:String)=viewModelScope.launch {
            callMedicineSearchApi(keywords)
    }

    private suspend fun callMedicineSearchApi(keywords: String){
          medicine.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.searchMedicine(keywords)
                medicine.postValue(handleMedicineResponse(response))
            }
            else
                medicine.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> medicine.postValue(Resource.Error("Network Failure"))
                else -> medicine.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleMedicineResponse(response: Response<ArrayList<MedicineResponse>>): Resource<ArrayList<MedicineResponse>>{
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                    medicineResponse = resultResponse
                    return Resource.Success(medicineResponse ?: resultResponse)

            }
        }
        else{
            val commonResponse = Gson().fromJson( response.errorBody()!!.string(), CommonResponse::class.java)
            return Resource.Error(commonResponse.message)
        }
        return Resource.Error(response.message())
    }

    }
