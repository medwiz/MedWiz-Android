package com.medwiz.medwiz.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.repository.patient.PatientRepoInterface
import com.medwiz.medwiz.repository.reviews.ReviewRepoInterface
import com.medwiz.medwiz.util.NetworkUtils
import com.medwiz.medwiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val repository: ReviewRepoInterface, @ApplicationContext private val context: Context):ViewModel() {


    val reviews:MutableLiveData<Resource<java.util.ArrayList<Review>>> = MutableLiveData()
    var reviewsResponse:java.util.ArrayList<Review>?=null



    public fun getAllReviewsByDoctor(token:String,email:String)=viewModelScope.launch {
        callReviewApi(token,email)
    }
    private suspend fun callReviewApi(token:String,email:String){
        reviews.postValue(Resource.Loading())
        try{
            if(NetworkUtils.isInternetAvailable(context)){
                val response = repository.getAllReviewByDoctor(token,email)
                reviews.postValue(handleGetUserResponse(response))
            }
            else
                reviews.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> reviews.postValue(Resource.Error("Network Failure"))
                else -> reviews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleGetUserResponse(response: Response<ArrayList<Review>>): Resource<ArrayList<Review>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(resultResponse.size>0) {
                    reviewsResponse = resultResponse
                    return Resource.Success(reviewsResponse ?: resultResponse)
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