package com.medwiz.medwiz.patientsView.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SingleBookingRcvItemBinding
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.patientsView.patientsUi.booking.BookingItemListener
import com.medwiz.medwiz.util.UtilConstants
import java.util.ArrayList

class PatientBookingAdapter(private val context: Context,
                            private val itemList:ArrayList<Consultation>,
                            private val listener: BookingItemListener,
                            private val status:String
):RecyclerView.Adapter<PatientBookingAdapter.PatientBookingAdapterViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientBookingAdapterViewHolder {
        val binding = SingleBookingRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PatientBookingAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientBookingAdapterViewHolder, position: Int) {
        val reviewItem = itemList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class PatientBookingAdapterViewHolder(val binding: SingleBookingRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Consultation, position: Int){
           binding.nameTextView.text = item.doctorName
           binding.tvSpecialization.text=item.specialization
            val timeAndDate=item.consDate+" "+item.consTime
           binding. tvTimeAndDate.text=timeAndDate
            if(status==UtilConstants.STATUS_UPCOMING){
                binding.tvStatus.setTextColor(context.getColor(R.color.blue))
            }
            if(status==UtilConstants.STATUS_COMPLETED){
                binding.tvStatus.setTextColor(context.getColor(R.color.green))
            }
            binding.tvStatus.text=item.status
           
        }
    }
}