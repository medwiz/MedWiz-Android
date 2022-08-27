package com.medwiz.medwiz.patientsView.patientsUi.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleDoctorRcvItemBinding
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientModels.Doctors

class DoctorsAdapter(private val context: Context,
                     private val itemList:MutableList<Doctors>, private val listener: HomeScreenListener
):RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val binding = SingleDoctorRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return DoctorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class DoctorsViewHolder(val binding: SingleDoctorRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(doctorItem: Doctors, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
            binding.layMain.setOnClickListener {
                listener.onClickConsult(position,doctorItem)
            }
        }
    }
}