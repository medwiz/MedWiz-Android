package com.medwiz.medwiz.doctorsView.docotorUi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityDoctorsBinding
import com.medwiz.medwiz.databinding.ActivityPatientBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_doctor_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomDoctorBar.setupWithNavController(
            navHostFragment.navController
        )

        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.doctorDetails -> {
                        hideBottomLayout()
                    }
                    R.id.bookAppointmentFragment -> {
                        hideBottomLayout()
                    }
                    R.id.paymentFragment -> {
                        hideBottomLayout()
                    }


                    else -> showBottomLayout()
                }
            }


    }

    private fun hideBottomLayout() {
        binding.bottomDoctorBar.visibility = View.GONE
    }
    private fun showBottomLayout() {
        binding.bottomDoctorBar.visibility  = View.VISIBLE
    }



}