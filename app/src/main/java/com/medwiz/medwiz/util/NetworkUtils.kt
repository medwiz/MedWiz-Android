package com.medwiz.medwiz.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtils {

    companion object {

        fun isInternetAvailable(context: Context): Boolean {
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    ) ?: false
                } else {
                    (@Suppress("DEPRECATION")
                    return this.activeNetworkInfo?.isConnected ?: false)
                }
            }
        }

        fun isInternetAvailable2(context: Context):Boolean{
            val connectivityManager = context.getSystemService(
                ConnectivityManager::class.java
            ) as ConnectivityManager
            val currentNetwork: Network? = connectivityManager.activeNetwork

            return currentNetwork != null
        }
    }
}