package com.submission.propertyapp.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("page")
    val  page: String,
    @SerializedName("per_page")
    val  perPage: String,
    @SerializedName("search")
    val  search: String,
    @SerializedName("total_page")
    val  totalPage: Int,
    @SerializedName("total_row")
    val  totalRow: Int
)