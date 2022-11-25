package com.medwiz.medwiz.repository.file

import com.google.gson.JsonObject
import com.medwiz.medwiz.data.reponse.CommonResponse
import com.medwiz.medwiz.data.reponse.FileResponse
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface FileRepoInterface {

    suspend fun uploadFile(file: MultipartBody.Part):Response<FileResponse>

}