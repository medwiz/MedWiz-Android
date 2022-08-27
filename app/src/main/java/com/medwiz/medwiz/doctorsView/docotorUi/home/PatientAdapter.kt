package com.medwiz.medwiz.doctorsView.docotorUi.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SinglePatientRcvItemBinding
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener
import com.medwiz.medwiz.patientsView.patientModels.Doctors
import com.medwiz.medwiz.util.UtilConstants

class PatientAdapter(private val context: Context,
                     private val type:String,
                     private val itemList:MutableList<Doctors>,
                     private val listener: HomeScreenListener
):RecyclerView.Adapter<PatientAdapter.PatientViewHolder>(){





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
        fun bind(doctorItem: Doctors, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
            binding.layMain.setOnClickListener {
                listener.onClickConsult(position,doctorItem)
            }
            when(type){
                UtilConstants.TYPE_UPCOMING->{
                    binding.imgStatus.visibility= View.GONE
                }
                UtilConstants.TYPE_COMPLETED->{
                    binding.imgStatus.setImageResource(R.drawable.ic_completed_appointment)
                    binding.imgStatus.visibility= View.VISIBLE
                }
                UtilConstants.TYPE_CANCELLED->{
                    binding.imgStatus.visibility= View.VISIBLE
                    binding.imgStatus.setImageResource(R.drawable.ic_cancelled_appointment)
                }
            }
        }
    }
}