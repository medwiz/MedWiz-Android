package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SelectDateSingleLayoutBinding
import com.medwiz.medwiz.model.Review

class SelectDateAdapter(private val context: Context,
                        private val itemList:MutableList<Review>
):RecyclerView.Adapter<SelectDateAdapter.SelectDateViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDateViewHolder {
        val binding = SelectDateSingleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return SelectDateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectDateViewHolder, position: Int) {
        val reviewItem = itemList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class SelectDateViewHolder(val binding: SelectDateSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewItem: Review, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
           
        }
    }
}