package com.medwiz.medwiz.patientsView.patientsUi.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.ReviewSingleLayoutBinding
import com.medwiz.medwiz.model.Review
import java.util.ArrayList

class ReviewAdapter(private val context: Context):RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){

    private var itemList:ArrayList<Review> = ArrayList()

    public fun setData(list:ArrayList<Review>){
          this.itemList=list
          notifyDataSetChanged()
    }


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
        fun bind(reviewItem: Review, position: Int){
            binding.tvHeading.text= reviewItem.heading
            binding.rbRating.rating = reviewItem.rating.toFloat()
            binding.tvReviewComment.text=reviewItem.reviewerName
            binding.tvReviewerName.text=reviewItem.reviewerName
           
        }
    }
}