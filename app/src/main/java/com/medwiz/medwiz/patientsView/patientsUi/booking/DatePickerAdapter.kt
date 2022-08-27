package com.medwiz.medwiz.patientsView.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medwiz.medwiz.R
import com.medwiz.medwiz.patientsView.patientModels.BookingDate

class DatePickerAdapter( private val mContext: Context, private val listener:BookingListener) :
    RecyclerView.Adapter<DatePickerAdapter.ViewHolder>() {
    private val mDataset = ArrayList<BookingDate>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewDate: TextView = itemView.findViewById<View>(R.id.tvDate) as TextView
        var mTextViewDay: TextView = itemView.findViewById<View>(R.id.tvDay) as TextView
        var rlMainLay: LinearLayout = itemView.findViewById<View>(R.id.rlMain) as LinearLayout
    }

    fun setData(listModel: List<BookingDate>) {
        mDataset.clear()
        mDataset.addAll(listModel)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mDataset.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataset[position]
        if(item.isSelected){
            holder.rlMainLay.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.rounded_corner_yellow
                )
            )
        }else{
            holder.rlMainLay.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.rounded_corner_blue
                )
            )
        }


        holder.mTextViewDate.text = item.bookingDate
        holder.mTextViewDay.text = item.bookingDay
        holder.rlMainLay.setOnClickListener {
            listener.onClickDate(position,item)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.booking_date_item, parent, false
        )
        return ViewHolder(view)
    }
}