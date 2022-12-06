package com.medwiz.medwiz.patientsView.main

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
    private var userDetails: LoginResponse?=null
    private var mCustomLoader: CustomLoaderDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        userDetails=intent.getParcelableExtra(UtilConstants.userDetails)
//        setUserDetails(userDetails!!)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_patient_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mCustomLoader = CustomLoaderDialog(this, true)
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

    public fun setUserDetails(data: LoginResponse) {
        this.userDetails=data
    }

    public fun getUserDetails():LoginResponse{
        return this.userDetails!!
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




