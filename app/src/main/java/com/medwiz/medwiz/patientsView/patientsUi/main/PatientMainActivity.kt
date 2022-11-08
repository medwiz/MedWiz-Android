package com.medwiz.medwiz.patientsView.patientsUi.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.ActivityPatientBinding
import com.medwiz.medwiz.main.DefaultFragmentFactory
import com.medwiz.medwiz.util.CustomLoaderDialog
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PatientMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory
    private var mCustomLoader: CustomLoaderDialog? = null
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



    fun showLoading() {
        if (mCustomLoader?.window != null) {
            (mCustomLoader?.window)!!.setBackgroundDrawableResource(android.R.color.transparent)
            mCustomLoader?.show()
        }
    }

    fun hideLoading() {
        if (mCustomLoader != null) mCustomLoader?.cancel()
    }
    }


