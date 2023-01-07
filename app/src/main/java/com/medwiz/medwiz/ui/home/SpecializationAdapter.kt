package com.medwiz.medwiz.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleItemSpecializationBinding
import com.medwiz.medwiz.model.Specialization
import java.util.ArrayList

class SpecializationAdapter(private val context: Context
):RecyclerView.Adapter<SpecializationAdapter.SpecializationViewHolder>(){

    private var itemList:ArrayList<Specialization> = ArrayList()

   public fun setData(list:ArrayList<Specialization>){
       this.itemList=list
       notifyDataSetChanged()
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecializationViewHolder {
        val binding = SingleItemSpecializationBinding.inflate(LayoutInflater.from(context),parent,false)
        return SpecializationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecializationViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class SpecializationViewHolder(val binding: SingleItemSpecializationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Specialization, position: Int){
            binding.imgAvatar.setImageDrawable(item.image)
            binding.tvName.text=item.name
        }
    }
}