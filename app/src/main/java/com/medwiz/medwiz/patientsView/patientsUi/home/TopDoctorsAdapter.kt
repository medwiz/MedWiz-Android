package com.medwiz.medwiz.patientsView.patientsUi.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleDoctorRcvItemBinding
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import java.util.ArrayList

class TopDoctorsAdapter(private val context: Context,
                        private val listener: HomeScreenListener
):RecyclerView.Adapter<TopDoctorsAdapter.DoctorsViewHolder>(){

    private var doctorList:ArrayList<DoctorResponse> = ArrayList()

   public fun setData(list:ArrayList<DoctorResponse>){
       this.doctorList=list
       notifyDataSetChanged()
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val binding = SingleDoctorRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return DoctorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val foodItem = doctorList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    inner class DoctorsViewHolder(val binding: SingleDoctorRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(doctorItem: DoctorResponse, position: Int){
            binding.nameTextView.text = doctorItem.firstname+" "+doctorItem.lastname
            binding.layMain.setOnClickListener {
                listener.onClickConsult(position,doctorItem)
            }
        }
    }
}