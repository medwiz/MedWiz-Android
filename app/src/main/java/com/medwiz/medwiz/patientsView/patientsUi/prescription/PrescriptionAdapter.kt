package com.medwiz.medwiz.patientsView.patientsUi.prescription

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.PrescriptionSingleItemBinding
import com.medwiz.medwiz.databinding.SingleDoctorRcvItemBinding
import com.medwiz.medwiz.model.Prescription
import com.medwiz.medwiz.patientsView.booking.PrescriptionItemListener
import java.util.ArrayList

class PrescriptionAdapter(private val context: Context, private val listener: PrescriptionItemListener,
):RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder>(){

    private var prescriptionList:ArrayList<Prescription> = ArrayList()

   public fun setData(list:ArrayList<Prescription>){
       this.prescriptionList=list
       notifyDataSetChanged()
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionViewHolder {
        val binding = PrescriptionSingleItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PrescriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrescriptionViewHolder, position: Int) {
        val foodItem = prescriptionList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return prescriptionList.size
    }

    inner class PrescriptionViewHolder(val binding: PrescriptionSingleItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Prescription, position: Int){

            binding.tvDocName.text=item.docName
            binding.tvDate.text=item.updateDate
            binding.tvDate.setOnClickListener {
                listener.onClickPrescription(position,item)
            }

        }
    }
}