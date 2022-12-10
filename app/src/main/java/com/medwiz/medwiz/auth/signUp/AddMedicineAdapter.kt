package com.medwiz.medwiz.auth.signUp

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleAddMedicineBinding
import java.util.*

class AddMedicineAdapter(private val context: Context,val listener: AddMedicineListener
):RecyclerView.Adapter<AddMedicineAdapter.AddMedicineViewHolder>(){

    private var viewCount:ArrayList<AddView> = ArrayList()

    fun setData(viewSize:ArrayList<AddView>,position: Int){
        this.viewCount=viewSize
        notifyItemInserted(position+1)
    }
    fun setRemoveData(viewSize:ArrayList<AddView>){
        this.viewCount=viewSize
       notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMedicineViewHolder {
        val binding = SingleAddMedicineBinding.inflate(LayoutInflater.from(context),parent,false)
        return AddMedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddMedicineViewHolder, position: Int) {
        val itemObj=viewCount[position]
        holder.bind(itemObj,position)
    }

    override fun getItemCount(): Int {
        return viewCount.size;
    }

    inner class AddMedicineViewHolder(val binding: SingleAddMedicineBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:AddView,position: Int){
            binding.btSave.setOnClickListener {
              val brandName=binding.etBrandName.text.toString()
              val mrp=binding.etMrp.text.toString()
              if(brandName.isNotEmpty()&&mrp.isNotEmpty()){
                  item.brandName=brandName
                  item.mrp=mrp.toDouble()
                  item.isSaved=true
                  listener.onClickAddView(item,position)
              }
          }

            binding.imgDelete.setOnClickListener {
                val brandName=binding.etBrandName.text.toString()
                val mrp=binding.etMrp.text.toString()
                if(brandName.isNotEmpty()&&mrp.isNotEmpty()){
                 listener.onItemDelete(item,position)
                }
            }

            binding.etBrandName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val i=0
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val i=0
                }
            })

            binding.etMrp.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val i=0
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val i=0
                }
            })
        }
    }
}