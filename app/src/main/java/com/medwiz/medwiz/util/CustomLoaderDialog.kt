package com.medwiz.medwiz.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.medwiz.medwiz.databinding.CustomDialogBinding


class CustomLoaderDialog (context: Context) : Dialog(context) {

    private lateinit var mCustomDialogBinding: CustomDialogBinding
    private var enableLottie: Boolean = false

    constructor(context: Context, enableLottie: Boolean = false) : this(context) {
        this.enableLottie = enableLottie
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        mCustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
        setContentView(mCustomDialogBinding.root)
        setCancelable(false)

        if (enableLottie) {
            mCustomDialogBinding.progressBar.visibility = View.VISIBLE
            // mCustomDialogBinding.avIndicator.visibility = View.GONE
        } else {
            mCustomDialogBinding.progressBar.visibility = View.GONE
//            mCustomDialogBinding.avIndicator.visibility = View.VISIBLE
//            mCustomDialogBinding.avIndicator.show()
        }

    }

}

