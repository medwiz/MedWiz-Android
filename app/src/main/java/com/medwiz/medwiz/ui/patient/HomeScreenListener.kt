package com.medwiz.medwiz.ui.patient

import com.medwiz.medwiz.models.Doctors

interface HomeScreenListener {

    fun onClickConsult(position: Int,doctor:Doctors)


}