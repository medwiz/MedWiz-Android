package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.PrescriptionSingleLayoutBinding
import com.medwiz.medwiz.doctorsView.model.Medication

class PrescriptionAdapter(private val context: Context,private val type:String
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
            when(type){
                context.getString(R.string.add_medicine_title)->{
                    setUiForMedicine(binding,medicineItem)
                }
                context.getString(R.string.add_test_title)->{
                    setUiForLabTest(binding,medicineItem)
                }
            }



           
        }
    }

    private fun setUiForMedicine(binding: PrescriptionSingleLayoutBinding,medicineItem: Medication){
        binding.tvName.text=medicineItem.name
        var mor=medicineItem.morningDose
        var aft=medicineItem.afternoonDose
        var night=medicineItem.nightDose
        val days=" ("+medicineItem.noOfDays.toString()+" days)"

        binding.tvTime.text =""+mor+"-"+aft+"-"+night+days
    }

    private fun setUiForLabTest(binding: PrescriptionSingleLayoutBinding,medicineItem: Medication){
        binding.tvName.text=medicineItem.labTestName

    }
}