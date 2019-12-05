package com.submission.propertyapp.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("token_expired")
    val tokenExpired: String,
    @SerializedName("username")
    val username: String
)