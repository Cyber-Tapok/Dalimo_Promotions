package com.tapok.dalimopromotions.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Promotion(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("cover") val coverUrl: String?,
    @SerializedName("photo") val photo: Photo?
) : Parcelable