package com.medwiz.medwiz.repository.file

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.network.AuthApi
import com.medwiz.medwiz.data.network.DoctorApi
import com.medwiz.medwiz.data.network.FileApi
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.FileResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class FileRepository @Inject constructor(private val api: FileApi):FileRepoInterface  {


    override suspend fun uploadFile(file: MultipartBody.Part): Response<FileResponse> {

        return api.upload(file)
    }


}