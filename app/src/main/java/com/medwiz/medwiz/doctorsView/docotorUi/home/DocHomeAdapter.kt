package com.medwiz.medwiz.ui.docotor.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.medwiz.medwiz.databinding.SinglePatientRcvItemBinding
import com.medwiz.medwiz.main.mainModels.Patient
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener

class DocHomeAdapter(private val context: Context,
                     private val itemList:MutableList<Patient>, private val listener: HomeScreenListener
):RecyclerView.Adapter<DocHomeAdapter.PatientViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = SinglePatientRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class PatientViewHolder(val binding: SinglePatientRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(patientItem: Patient, position: Int){
            binding.layMain.setOnClickListener {
               // listener.onClickConsult(position,patientItem)
            }
        }
    }
}