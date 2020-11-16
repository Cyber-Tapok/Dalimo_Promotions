package com.tapok.dalimopromotions.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("path_url") val pathUrl: String
)