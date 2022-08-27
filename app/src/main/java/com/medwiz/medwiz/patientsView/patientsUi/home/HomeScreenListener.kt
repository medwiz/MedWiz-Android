package com.medwiz.medwiz.patientsView.booking.patient.home

import com.medwiz.medwiz.patientsView.patientModels.Doctors

interface HomeScreenListener {

    fun onClickConsult(position: Int,doctor: Doctors)


}