package com.medwiz.medwiz.patientsView.patientsUi.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.ActivityPatientBinding
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PatientMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    private var userDetails: LoginResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        userDetails = intent.getParcelableExtra<LoginResponse>(UtilConstants.login_response)!!
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

    public fun getUserDetails():LoginResponse{
        return this.userDetails!!
    }
    }


