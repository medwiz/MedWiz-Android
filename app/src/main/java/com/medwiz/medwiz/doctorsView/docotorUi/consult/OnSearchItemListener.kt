package com.medwiz.medwiz.doctorsView.docotorUi.consult

import com.medwiz.medwiz.data.reponse.MedicineResponse

/**
 * @Author: Prithwiraj Nath
 * @Date:11/12/22
 */
interface OnSearchItemListener {

    fun onItemClick(obj:MedicineResponse,position:Int)
}