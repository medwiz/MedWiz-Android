package com.medwiz.medwiz.ui.patient.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleWorkingTimeBinding
import com.medwiz.medwiz.models.Doctors


class WorkingTimeAdapter (private val context: Context,
                          private val itemList:MutableList<Doctors>
): RecyclerView.Adapter<WorkingTimeAdapter.WorkingTimeViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkingTimeViewHolder {
        val binding = SingleWorkingTimeBinding.inflate(LayoutInflater.from(context),parent,false)
        return WorkingTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkingTimeViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class WorkingTimeViewHolder(val binding: SingleWorkingTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(doctorItem: Doctors, position: Int){

        }
    }
}