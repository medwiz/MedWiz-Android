package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.PrescriptionSingleLayoutBinding
import com.medwiz.medwiz.model.Medication


class PrescriptionAdapter(private val context: Context,private val type:String,private val listener: PrescriptionListener
):RecyclerView.Adapter<PrescriptionAdapter.PrescriptionAdapterViewHolder>(){

   private var precList=java.util.ArrayList<Medication>()

   fun setData(itemList: java.util.ArrayList<Medication>){
       this.precList=itemList
       notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionAdapterViewHolder {
        val binding = PrescriptionSingleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return PrescriptionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrescriptionAdapterViewHolder, position: Int) {
        val reviewItem = precList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return precList.size
    }

    inner class PrescriptionAdapterViewHolder(val binding: PrescriptionSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(medicineItem: Medication, position: Int){
            binding.tvNo.text=(position+1).toString()+"."
            if(medicineItem.dosage.isEmpty()){
                binding.tvDosage.visibility=View.GONE
            }
            binding.tvDosage.setOnClickListener {
                listener.onClickDosage(medicineItem, position)
            }
            when(type){
                context.getString(R.string.add_medicine_title)->{
                   // binding.tvDosage.visibility=View.VISIBLE
                    setUiForMedicine(binding,medicineItem)
                }
                context.getString(R.string.add_test_title)->{
                    //binding.tvDosage.visibility=View.GONE
                    setUiForLabTest(binding,medicineItem)
                }
            }



           
        }
    }

    private fun setUiForMedicine(binding: PrescriptionSingleLayoutBinding,medicineItem: Medication){
        binding.tvName.text=medicineItem.name

    }

    private fun setUiForLabTest(binding: PrescriptionSingleLayoutBinding,medicineItem: Medication){
        binding.tvName.text=medicineItem.name

    }
}