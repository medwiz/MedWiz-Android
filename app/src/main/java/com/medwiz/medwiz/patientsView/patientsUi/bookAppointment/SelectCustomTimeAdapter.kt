package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.SelectTimeSingleLayoutBinding
import com.medwiz.medwiz.model.CustomTimeEntity
import com.medwiz.medwiz.patientsView.patientsUi.doctorDetails.ReviewAdapter
import java.util.ArrayList

class SelectCustomTimeAdapter(private val context: Context,
                              private val listener:SelectedTimeListener,
                              private val itemList:ArrayList<CustomTimeEntity>
):RecyclerView.Adapter<SelectCustomTimeAdapter.SelectTimeViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeViewHolder {
        val binding = SelectTimeSingleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return SelectTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectTimeViewHolder, position: Int) {
        val customTime = itemList[position]
        holder.bind(customTime,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class SelectTimeViewHolder(val binding: SelectTimeSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(customTime: CustomTimeEntity, position: Int){
            if(customTime.isSelected){
                binding.tvTime.setTextColor(ContextCompat.getColor(context, R.color.blue))
            }else{
                binding.tvTime.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            binding.tvTime.text=customTime.time+" "+customTime.amOrPm
            binding.liMain.setOnClickListener {
                customTime.isSelected=true
                listener.onSelectedTime(position,customTime)
            }
           
        }
    }
}