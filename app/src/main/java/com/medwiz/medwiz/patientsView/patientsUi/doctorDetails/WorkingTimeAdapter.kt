package com.medwiz.medwiz.patientsView.booking.doctorDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleWorkingTimeBinding
import com.medwiz.medwiz.patientsView.patientModels.ReviewModel
import java.util.ArrayList


class WorkingTimeAdapter (private val context: Context,
                          private val itemList:ArrayList<String>
): RecyclerView.Adapter<WorkingTimeAdapter.WorkingTimeViewHolder>(){





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
        fun bind(workingTimeItem: String, position: Int){
            binding.tvDayOfWeek.text=workingTimeItem
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