package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.ReviewSingleLayoutBinding
import com.medwiz.medwiz.databinding.SelectTimeSingleLayoutBinding
import com.medwiz.medwiz.patientsView.patientModels.ReviewModel

class SelectTimeAdapter(private val context: Context,
                        private val itemList:MutableList<ReviewModel>
):RecyclerView.Adapter<SelectTimeAdapter.SelectTimeViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeViewHolder {
        val binding = SelectTimeSingleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return SelectTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectTimeViewHolder, position: Int) {
        val reviewItem = itemList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class SelectTimeViewHolder(val binding: SelectTimeSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewItem: ReviewModel, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
           
        }
    }
}