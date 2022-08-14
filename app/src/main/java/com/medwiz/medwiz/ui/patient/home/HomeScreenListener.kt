package com.medwiz.medwiz.ui.patient.home

import com.medwiz.medwiz.models.Doctors

interface HomeScreenListener {

    fun onClickConsult(position: Int,doctor:Doctors)


}