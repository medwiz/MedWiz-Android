package com.medwiz.medwiz.util

object UtilConstants {

    const val fileName = "medwizFile"
    const val name = "userName"
    const val loggedin = "loggedin"
    const val userId = "userId"
    const val userImage = "profilePic"
    const val accessToken = "medwizAccessToken"
    const val refreshToken = "medwizRefreshToken"
    const val nearbyDocs="NEAR_BY_DOCS"
    const val nearbyDocsValue=10
    const val baseurl = "https://eto-api-dev.azure-api.net/"

    const val register = "/auth/register/"
    const val login = "identity-int/api/caritag/Users/Login"
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
    const val GRANT_TYPE="grant_type"
    const val CLIENT_ID="client_id"
    const val CLIENT_SECRET="client_secret"
    const val GRANT_TYPE_VALUE="client_credentials"
    const val CLIENT_ID_VALUE_DEVICE="firmware-management"
    const val CLIENT_SECRET_VALUE_DEVICE="638ab4d4-b13a-4e81-be1d-ce32251d454e"//BuildConfig.CLIENT_SECRET_DEVICE
    const val CLIENT_ID_VALUE="eto-mobile-client"
    const val CLIENT_CREDENTIALS="client_credentials"
    const val CLIENT_SECRET_VALUE="311a80eb-3bc5-4c93-83ab-fe363a27afb1"
    const val ERROR_INVALID_USER="MSG_INVALID_USER_CREDENTIALS"
    const val ERROR_ACCOUNT_SETUP:String="MSG_ACCOUNT_IS_NOT_FULLY_SET_UP"

    //Profile Items
    const val ITEM_PROFILE=1
    const val ITEM_EDIT_PROFILE=2
    const val ITEM_SETTING=3
    const val ITEM_TERMS=4



}