package com.medwiz.medwiz.data.reponse

data class FileResponse(
    val downloadUrl: String,
    val fileName: String,
    val fileSize: Int,
    val fileType: String
)