package com.medwiz.medwiz.shopView.adaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.databinding.SingleOrderRcvItemBinding
import com.medwiz.medwiz.model.Order


import java.util.ArrayList

class OrderAdapter(private val context: Context
):RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(){

    private var itemList:ArrayList<Order> = ArrayList()

    public fun setData(list:ArrayList<Order>){
        this.itemList=list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = SingleOrderRcvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val foodItem = itemList[position]
        holder.bind(foodItem,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class OrderViewHolder(val binding: SingleOrderRcvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(orderItem: Order, position: Int){
          binding.userName.text=orderItem.customerName
            binding.address.text  =orderItem.address
            binding.orderTime.text=orderItem.orderTime
            binding.paymentMode.text=orderItem.paymentMode

        }
    }
}