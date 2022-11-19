package com.medwiz.medwiz.util

object UtilConstants {
    const val consultation: String="consultation"
    const val docId:String="docId"
    const val fees: String="fees"
    const val gender: String="gender"
    const val about: String="about"
    const val specialization: String="specialization"
    const val licencePath: String="licencePath"
    const val experience: String="experience"
    const val reviewList:String="reviewList"
    const val workingTimeList:String="workingTimeList"
    const val isActivated: String="isActivated"
    const val doctorInfo: String="doctorInfo"
    const val address: String="address"
    const val age: String="age"
    const val credit: String="credit"
    const val email: String="email"
    const val firstname: String="firstname"
    const val lastname: String="lastname"
    const val mobile: String="mobile"
    const val password: String="password"
    const val pinCode: String="pinCode"
    const val userType: String="userType"
    val request: String="request"
    const val fileName = "medwizFile"
    const val name = "userName"
    const val unauthorized="Unauthorized"
    const val login_response = "loginResponse"
    const val userId = "userId"
    const val userImage = "profilePic"
    const val accessToken = "medwizAccessToken"
    const val refreshToken = "medwizRefreshToken"
    const val nearbyDocs="NEAR_BY_DOCS"
    const val nearbyDocsValue=10
    const val baseurl = "http://10.0.2.2:8080/api/v1/"

    const val register = "auth/register"
    const val login = "auth/login"
    const val getUserById="user"
    const val doctor="doctor"
    const val review="review/doctor/"
    const val getDoctorByEmail="doctor/email/"
    const val getPatientByEmail="user/email/"
    const val doctorApi="doctor/"
    const val consultationApi="consult/"
    const val refresh = "identity-int/api/caritag/protocol/openid-connect/token"

    const val trending = "/api/posts/?ordering=likes&limit=6"
    const val blogs = "/api/posts/"
    const val likeBlog = "/api/likes/create/?type=post"
    const val commentBlog = "/api/comments/create/?type=post"

    const val newBlog = "/api/posts/create/"

    const val technology = "Technology"
    const val global = "Global Affairs"
    const val health = "Health"
    const val sports = "Sports"
    const val science = "Science"
    const val entertainment = "Entertainment"

    const val USERNAME = "username"
    const val PASSWORD ="password"
    const val TYPE_UPCOMING="upcoming"
    const val TYPE_COMPLETED="completed"
    const val TYPE_CANCELLED="cancelled"

    const val STATUS_IN_PROGRESS = "inProgress"
    const val STATUS_ON_HOLD = "onHold"
    const val STATUS_COMPLETED = "completed"
    const val STATUS_CANCELED = "canceled"


    //Profile Items
    const val ITEM_PROFILE=1
    const val ITEM_EDIT_PROFILE=2
    const val ITEM_SETTING=3
    const val ITEM_LOGOUT=4
    const val ITEM_TERMS=5



}