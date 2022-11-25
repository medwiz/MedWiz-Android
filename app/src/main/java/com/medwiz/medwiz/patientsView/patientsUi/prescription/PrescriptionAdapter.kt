package com.medwiz.medwiz.patientsView.patientsUi.prescription

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleDoctorRcvItemBinding
import com.medwiz.medwiz.model.Prescription
import java.util.ArrayList

class PrescriptionAdapter(private val context: Context,
):RecyclerView.Adapter<PrescriptionAdapter.DoctorsViewHolder>(){

    private var prescriptionList:ArrayList<Prescription> = ArrayList()

   public fun setData(list:ArrayList<Prescription>){
       this.prescriptionList=list
       notifyDataSetChanged()
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val binding = SingleDoctorRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return DoctorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val foodItem = prescriptionList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return prescriptionList.size
    }

    inner class DoctorsViewHolder(val binding: SingleDoctorRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(Item: Prescription, position: Int){

        }
    }
}