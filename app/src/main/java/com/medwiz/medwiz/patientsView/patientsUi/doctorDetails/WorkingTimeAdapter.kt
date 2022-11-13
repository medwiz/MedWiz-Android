package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleWorkingTimeBinding
import com.medwiz.medwiz.model.WorkTimings
import java.util.ArrayList


class WorkingTimeAdapter (private val context: Context
): RecyclerView.Adapter<WorkingTimeAdapter.WorkingTimeViewHolder>(){

    private var itemList:ArrayList<WorkTimings> = ArrayList()
   public fun setData(list:ArrayList<WorkTimings>){

       this.itemList=list
       notifyDataSetChanged()
   }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkingTimeViewHolder {
        val binding = SingleWorkingTimeBinding.inflate(LayoutInflater.from(context),parent,false)
        return WorkingTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkingTimeViewHolder, position: Int) {
        val workingTimeItem = itemList[position]
        holder.bind(workingTimeItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class WorkingTimeViewHolder(val binding: SingleWorkingTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(workingTimeItem: WorkTimings, position: Int){
            binding.tvDayOfWeek.text=workingTimeItem.day
            binding.tvStartTime.text=workingTimeItem.startTime+ "-" +workingTimeItem.endTime
           // binding.tvEndTime.text=workingTimeItem.endTime
//            when(position){
//                0->{ binding.tvDayOfWeek.text="Monday"}
//                1->{ binding.tvDayOfWeek.text="Tuesday"}
//                2->{ binding.tvDayOfWeek.text="Wednesday"}
//                3->{ binding.tvDayOfWeek.text="Thursday"}
//                4->{ binding.tvDayOfWeek.text="Friday"}
//                5->{ binding.tvDayOfWeek.text="Saturday"}
//                6->{ binding.tvDayOfWeek.text="Sunday"}
//            }

        }
    }
}