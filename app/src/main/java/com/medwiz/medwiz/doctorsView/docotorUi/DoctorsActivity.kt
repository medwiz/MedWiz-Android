package com.medwiz.medwiz.doctorsView.docotorUi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.data.reponse.LoginResponse
import com.medwiz.medwiz.databinding.ActivityDoctorsBinding
import com.medwiz.medwiz.databinding.ActivityPatientBinding
import com.medwiz.medwiz.main.DefaultFragmentFactory
import com.medwiz.medwiz.util.CustomLoaderDialog
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoctorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorsBinding
    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory
    private var mCustomLoader: CustomLoaderDialog? = null
    private var userDetails: LoginResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_doctor_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mCustomLoader = CustomLoaderDialog(this, true)
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
                    R.id.docHomeFragment->{
                        val i=0
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
    fun showLoading() {
        if (mCustomLoader?.window != null) {
            (mCustomLoader?.window)!!.setBackgroundDrawableResource(android.R.color.transparent)
            mCustomLoader?.show()
        }
    }

    fun hideLoading() {
        if (mCustomLoader != null) mCustomLoader?.cancel()
    }

    fun setUserDetails(userDetails:LoginResponse){
        this.userDetails=userDetails;
    }

    fun getUserDetails():LoginResponse{
        return this.userDetails!!
    }



}