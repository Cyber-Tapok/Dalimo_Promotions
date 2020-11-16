package com.tapok.dalimopromotions.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("last_page") val lastPage: Int
)