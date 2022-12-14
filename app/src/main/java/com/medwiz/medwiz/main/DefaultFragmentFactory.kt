package com.medwiz.medwiz.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.medwiz.medwiz.auth.login.LoginFragment
import com.medwiz.medwiz.auth.signUp.SignUpFragment
import com.medwiz.medwiz.auth.SelectAccountFragment


import javax.inject.Inject

class DefaultFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String): Fragment {

        return when (className) {
            SelectAccountFragment::class.java.name-> SelectAccountFragment()
            LoginFragment::class.java.name-> LoginFragment()
            SignUpFragment::class.java.name-> SignUpFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}