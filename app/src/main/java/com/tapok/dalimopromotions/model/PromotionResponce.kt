package com.tapok.dalimopromotions.model

import com.google.gson.annotations.SerializedName

data class PromotionResponce (
    @SerializedName("data") val data: List<Promotion>
)