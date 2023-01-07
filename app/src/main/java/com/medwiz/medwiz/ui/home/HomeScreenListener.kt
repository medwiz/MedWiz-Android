package com.medwiz.medwiz.patientsView.patientsUi.home

import com.medwiz.medwiz.model.DoctorResponse

interface HomeScreenListener {

    fun onClickConsult(position: Int,doctor: DoctorResponse)



}