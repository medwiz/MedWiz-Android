package com.medwiz.medwiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.PatientResponse
import com.medwiz.medwiz.databinding.ActivityMainBinding
import com.medwiz.medwiz.main.DefaultFragmentFactory
import com.medwiz.medwiz.main.mainViewModels.MainViewModel
import com.medwiz.medwiz.util.CustomLoaderDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory
    private var mCustomLoader: CustomLoaderDialog? = null
    private val viewModel: MainViewModel by viewModels()
    private var userDetails:PatientResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mCustomLoader = CustomLoaderDialog(this, true)
        supportFragmentManager.fragmentFactory = fragmentFactory
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomBar.setupWithNavController(navHostFragment.navController)
        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.loginFragment -> {
                         hideBottomLayout()
                    }
                    R.id.homeFragment->{
                        showBottomLayout()
                    }
                    R.id.profileFragment->{
                        showBottomLayout()
                    }
                    R.id.appointmentFragment->{
                        showBottomLayout()
                    }
                    R.id.prescriptionFragment->{
                        showBottomLayout()
                    }


                     else ->  hideLoading()
                }
            }
    }

    private fun hideBottomLayout() {
         binding.bottomBar.visibility = View.GONE
    }

    private fun showBottomLayout() {
         binding.bottomBar.visibility = View.VISIBLE
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

    fun setUserDetails(data: PatientResponse?) {
        this.userDetails=data
    }
    fun getUserDetails():PatientResponse{
        return userDetails!!
    }

}