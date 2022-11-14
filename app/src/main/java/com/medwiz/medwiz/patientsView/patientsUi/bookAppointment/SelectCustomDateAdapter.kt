package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SelectDateSingleLayoutBinding
import com.medwiz.medwiz.model.CustomDateEntity

class SelectCustomDateAdapter(private val context: Context,
                              private val listener:SelectedDateListener,
                              private val itemList:MutableList<CustomDateEntity>
):RecyclerView.Adapter<SelectCustomDateAdapter.SelectDateViewHolder>(){





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
        fun bind(customDate: CustomDateEntity, position: Int){
            if(customDate.isSelected){
                binding.tvDay.setTextColor(ContextCompat.getColor(context, R.color.blue))
            }else{
                binding.tvDay.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            binding.tvDay.text=customDate.day
            binding.tvDate.text=customDate.date
            binding.liMain.setOnClickListener {
                customDate.isSelected=true
                listener.onSelectedDate(position,customDate)
               // binding.tvDay.setTextColor(ContextCompat.getColor(context, R.color.blue))
            }
           
        }
    }
}