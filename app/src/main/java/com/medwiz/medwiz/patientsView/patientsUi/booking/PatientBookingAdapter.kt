package com.medwiz.medwiz.patientsView.patientsUi.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleBookingRcvItemBinding
import com.medwiz.medwiz.model.BookingModel

class PatientBookingAdapter(private val context: Context,
                            private val itemList:MutableList<BookingModel>,
                            private val listener: BookingItemListener
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
        fun bind(reviewItem: BookingModel, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
           
        }
    }
}