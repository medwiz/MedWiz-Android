package com.medwiz.medwiz.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    var id: Long,
    var userId: String,
    var  paymentMode:String,
    var  orderTime:String,
    var customerName: String,
    var addedDate: String,
    var isActive: Boolean,
    var city: String,
    var state: String,
    var country: String,
    var pinCode: String,
    var email: String,
    var mobile: String,
    var password: String,
    var credit: Double,
    var status: String,
    var subTotal: Float,
    var tax: Float,
    var promoCode: String,
    var discount: Float,
    var grandTotal: Float,
    var metaData: String,
    var address: String,
    var updatedAt: String,
    var itemName: String,
    var itemQuantity: Int
):Parcelable
