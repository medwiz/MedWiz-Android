package com.medwiz.medwiz.patientsView.booking

import com.medwiz.medwiz.model.Prescription

interface PrescriptionItemListener {
    fun onClickPrescription(position: Int,itemObj:Prescription)

}