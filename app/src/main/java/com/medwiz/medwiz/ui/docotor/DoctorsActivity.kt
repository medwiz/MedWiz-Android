package com.medwiz.medwiz.ui.docotor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}