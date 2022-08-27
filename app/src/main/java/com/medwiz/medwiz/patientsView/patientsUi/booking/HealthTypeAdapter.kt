package com.medwiz.medwiz.patientsView.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.HealthTypeRcvItemBinding
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener

class HealthTypeAdapter(private val context: Context,
                        private val itemList:MutableList<Doctors>, private val listener: HomeScreenListener
):RecyclerView.Adapter<HealthTypeAdapter.DoctorsViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val binding = HealthTypeRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return DoctorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class DoctorsViewHolder(val binding: HealthTypeRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(doctorItem: Doctors, position: Int){
            if(position==0){
                binding.btType.text="Dental"
            }
            else if(position==1){
                binding.btType.text="Optometrist"
            }
            else{
                binding.btType.text="General"
            }
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName

            binding.layMain.setOnClickListener {
                listener.onClickConsult(position,doctorItem)
            }
        }
    }
}