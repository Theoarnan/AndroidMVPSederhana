package com.submission.propertyapp.model

import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @SerializedName("message")
    val  message: String
)