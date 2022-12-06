package com.medwiz.medwiz.patientsView.patientsUi.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleProfileItemBinding
import com.medwiz.medwiz.patientsView.booking.ProfileItemListener
import com.medwiz.medwiz.model.ProfileItemModel


class ProfileItemAdapter (private val context: Context,
                          private val itemList:MutableList<ProfileItemModel>,
                          private val listener:ProfileItemListener
): RecyclerView.Adapter<ProfileItemAdapter.ProfileItemViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val binding = SingleProfileItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProfileItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ProfileItemViewHolder(val binding: SingleProfileItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(profileItem: ProfileItemModel, position: Int){
            binding.tvItemName.text=profileItem.itemName
            binding.imgProfileItem.setImageResource(profileItem.itemIcon)
            binding.rlMain.setOnClickListener {
                listener.onClickItem(position,profileItem)
            }


        }
    }
}