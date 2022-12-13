package com.medwiz.medwiz.repository.search

import com.medwiz.medwiz.data.reponse.MedicineResponse
import retrofit2.Response
import java.util.ArrayList

/**
 * @Author: Prithwiraj Nath
 * @Date:11/12/22
 */
interface SearchRepoInterface {
    suspend fun searchMedicine(type:String,keyword:String,isMedicine:Boolean): Response<ArrayList<MedicineResponse>>
}