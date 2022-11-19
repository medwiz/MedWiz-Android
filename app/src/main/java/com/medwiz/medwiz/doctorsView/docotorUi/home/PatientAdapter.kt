package com.medwiz.medwiz.doctorsView.docotorUi.home

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SinglePatientRcvItemBinding
import com.medwiz.medwiz.doctorsView.docotorUi.ConsultationListener
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.model.Review
import com.medwiz.medwiz.patientsView.patientsUi.home.HomeScreenListener
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.UtilConstants
import java.util.ArrayList

class PatientAdapter(private val context: Context,
                     private val type:String,
                     private val listener: ConsultationListener
):RecyclerView.Adapter<PatientAdapter.PatientViewHolder>(){

    private var itemList:ArrayList<Consultation> = ArrayList()

    public fun setData(list:ArrayList<Consultation>){
        this.itemList=list
        notifyDataSetChanged()
    }


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
        fun bind(doctorItem: Consultation, position: Int){
            binding.tvPatientName.text = doctorItem.patientName
            binding.tvPatientGenderAndMobile.text= doctorItem.patientGender+" | "+doctorItem.patientMobile
            binding.tvDayAndDate.text=doctorItem.consDate
            binding.tvTime.text=doctorItem.consTime
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