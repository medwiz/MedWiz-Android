package com.medwiz.medwiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.ActivityMainBinding
import com.medwiz.medwiz.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.fragmentFactory = fragmentFactory

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        binding.botomNavView.setupWithNavController(
//            navHostFragment.navController
//        )

        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.selectAccountFragment -> {
                        // hideBottomLayout()
                    }


                    // else ->  binding.botomNavView.visibility = View.VISIBLE
                }
            }
    }

    private fun hideBottomLayout() {
        // binding.botomNavView.visibility = View.GONE
    }

    private fun showBottomLayout() {
        // binding.botomNavView.visibility = View.VISIBLE
    }

}