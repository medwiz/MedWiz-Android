package com.medwiz.medwiz.ui.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


            }
    }


