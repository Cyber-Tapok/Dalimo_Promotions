package com.tapok.dalimopromotions.model

import com.google.gson.annotations.SerializedName

data class ResponceApi (
    @SerializedName("data") val data: List<Promotion>
)