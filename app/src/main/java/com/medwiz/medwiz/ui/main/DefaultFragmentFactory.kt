package com.medwiz.medwiz.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.medwiz.medwiz.ui.auth.login.LoginFragment
import com.medwiz.medwiz.ui.auth.signUp.SignUpFragment
import com.medwiz.medwiz.ui.home.HomeFragment
import com.medwiz.medwiz.ui.profile.ProfileFragment


import javax.inject.Inject

class DefaultFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String): Fragment {

        return when (className) {
            LoginFragment::class.java.name-> LoginFragment()
            SignUpFragment::class.java.name-> SignUpFragment()
            HomeFragment::class.java.name->HomeFragment()
            ProfileFragment::class.java.name->ProfileFragment()

            else -> super.instantiate(classLoader, className)
        }
    }
}