package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.ReviewSingleLayoutBinding
import com.medwiz.medwiz.patientsView.patientModels.ReviewModel

class ReviewAdapter(private val context: Context,
                    private val itemList:MutableList<ReviewModel>
):RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewSingleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val reviewItem = itemList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ReviewViewHolder(val binding: ReviewSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewItem: ReviewModel, position: Int){
           // binding.nameTextView.text = doctorItem.firstName+" "+doctorItem.lastName
           
        }
    }
}