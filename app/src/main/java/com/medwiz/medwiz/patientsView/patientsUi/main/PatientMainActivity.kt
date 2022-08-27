package com.medwiz.medwiz.patientsView.booking.patient.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityPatientBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PatientMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_patient_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomBar.setupWithNavController(navHostFragment.navController)

        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.viewAllDoctorsFragment -> {
                        hideBottomLayout()
                    }
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
         binding.bottomBar.visibility = View.GONE
    }
    private fun showBottomLayout() {
        binding.bottomBar.visibility  = View.VISIBLE
    }
    }


