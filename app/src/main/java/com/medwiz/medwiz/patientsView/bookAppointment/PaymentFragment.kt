package com.medwiz.medwiz.patientsView.patientsUi.bookAppointment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentPaymentBinding
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.util.UtilConstants

class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private lateinit var binding: FragmentPaymentBinding
    private var newConsultation:Consultation?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentBinding.bind(view)

       this.newConsultation= arguments?.getParcelable<Consultation>(UtilConstants.consultation)!!
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.tvGotoHome.setOnClickListener{

            findNavController().navigate(R.id.action_paymentFragment_to_patientHomeFragment)
        }

        binding.btPay.setOnClickListener{
            hideViews()
            unHidePaymentCompleteViews()
        }

    }

    private fun unHidePaymentCompleteViews() {
        binding.imgComplete.visibility=View.VISIBLE
        binding.liBookingComplete.visibility=View.VISIBLE
        binding.tvGotoHome.visibility=View.VISIBLE

    }

    private fun hideViews() {
        binding.imgBack.visibility=View.GONE
        binding.tvPaymentGateWay.visibility=View.GONE
        binding.tvTitle.visibility=View.GONE
        binding.btPay.visibility=View.GONE
    }


}