package com.medwiz.medwiz.doctorsView.docotorUi.consult

import com.medwiz.medwiz.model.Medication

/**
 * @Author: Prithwiraj Nath
 * @Date:15/12/22
 */
interface PrescriptionListener {

    fun onClickDosage(obj:Medication,position: Int)
}