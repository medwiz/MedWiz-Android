package com.medwiz.medwiz.shopView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.ShopResponse
import com.medwiz.medwiz.databinding.ActivityShopBinding
import com.medwiz.medwiz.main.DefaultFragmentFactory
import com.medwiz.medwiz.util.CustomLoaderDialog
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class ShopActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShopBinding
    private var mCustomLoader: CustomLoaderDialog? = null
    private var shopResponse:ShopResponse?=null
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding =ActivityShopBinding.inflate(layoutInflater)
                setContentView(binding.root)
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_shop_nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                mCustomLoader = CustomLoaderDialog(this, true)
                binding.bottomBar.setupWithNavController(navHostFragment.navController)
                navController.addOnDestinationChangedListener { _, destination, _ ->
                        when (destination.id) {
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

    public fun setShopResponse(shopResponse:ShopResponse){
        this.shopResponse=shopResponse
    }

    public fun getShopResponse():ShopResponse{
        return this.shopResponse!!
    }
}