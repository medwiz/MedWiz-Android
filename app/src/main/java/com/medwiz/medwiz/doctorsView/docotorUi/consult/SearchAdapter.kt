package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.MedicineResponse
import com.medwiz.medwiz.databinding.PrescriptionSingleLayoutBinding
import com.medwiz.medwiz.databinding.SearchSingleItemBinding
import com.medwiz.medwiz.doctorsView.model.Medication
import java.util.ArrayList

class SearchAdapter(private val context: Context,private val mSearchList:ArrayList<MedicineResponse>,private  val listener:OnSearchItemListener,
                    private val isUpdate:Boolean
):RecyclerView.Adapter<SearchAdapter.PrescriptionAdapterViewHolder>(){

    var searchList=java.util.ArrayList<MedicineResponse>()
    init {
        this.searchList=mSearchList
    }

//   fun setData(itemList: java.util.ArrayList<MedicineResponse>){
//       this.searchList=itemList
//       notifyDataSetChanged()
//   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionAdapterViewHolder {
        val binding = SearchSingleItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PrescriptionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrescriptionAdapterViewHolder, position: Int) {
        val reviewItem = searchList[position]
        holder.bind(reviewItem,position)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class PrescriptionAdapterViewHolder(val binding: SearchSingleItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(medicineItem: MedicineResponse, position: Int){
            if(isUpdate){
                binding.tvName.text=medicineItem.name
            }else{
            binding.tvName.text=medicineItem.brand
            }
            binding.tvName.setOnClickListener {
                listener.onItemClick(medicineItem,position)
            }

        }
    }


}