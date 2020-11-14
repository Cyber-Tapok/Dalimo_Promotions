package com.tapok.dalimopromotions.model

import com.google.gson.annotations.SerializedName

data class PromotionResponse (
    @SerializedName("data") val data: List<Promotion>
)