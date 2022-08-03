package com.medwiz.medwiz.ui.patient.booking

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentDoctorBookingBinding
import com.medwiz.medwiz.models.BookingDate
import com.medwiz.medwiz.ui.patient.booking.DatePickerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorBookingFragment: Fragment(R.layout.fragment_doctor_booking),BookingListener {
    private lateinit var binding: FragmentDoctorBookingBinding
    private var adapter: DatePickerAdapter?=null
    val lis=ArrayList<BookingDate>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoctorBookingBinding.bind(view)
        binding.imgBack.setOnClickListener{

            findNavController().navigateUp()
        }

        binding.tv1.setOnClickListener {
            binding.tv2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv5.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            changeBackGround(binding.tv1)
        }
        binding.tv2.setOnClickListener {
            binding.tv1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv5.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            changeBackGround(binding.tv2)
        }
        binding.tv3.setOnClickListener {
            binding.tv1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv5.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            changeBackGround(binding.tv3)
        }
        binding.tv4.setOnClickListener {
            binding.tv1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv5.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            changeBackGround(binding.tv4)
        }
        binding.tv5.setOnClickListener {
            binding.tv1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            binding.tv4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layout_rounded_bg
                )
            )
            changeBackGround(binding.tv5)
        }

        binding.chkPayNow.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                binding.chkPayLater.isChecked=false
            }
        }
        binding.chkPayLater.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                binding.chkPayNow.isChecked=false
            }
        }

        val bookingDate1=BookingDate(1,"07","Sat")
        val bookingDate2=BookingDate(2,"08","Sun")
        val bookingDate3=BookingDate(3,"09","Mon")
        val bookingDate4=BookingDate(4,"10","Tue")
        val bookingDate5=BookingDate(5,"11","Wed")
        lis.add(bookingDate1)
        lis.add(bookingDate2)
        lis.add(bookingDate3)
        lis.add(bookingDate4)
        lis.add(bookingDate5)

        adapter = DatePickerAdapter(requireContext(),this)
        binding.rcvDate.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false)
        binding.rcvDate.adapter = adapter
        adapter!!.setData(lis)

    }

    private fun changeBackGround(tv: TextView) {
        tv.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.rounded_corner_yellow
            )
        )
    }

    override fun onClickDate(position: Int, dateObj: BookingDate) {
//       if(dateObj.isSelected){
//           dateObj.isSelected=false
//           adapter!!.setData(lis)
//       }
        for (item in lis){
            item.isSelected = item.id==dateObj.id
        }
        adapter!!.setData(lis)

    }
}