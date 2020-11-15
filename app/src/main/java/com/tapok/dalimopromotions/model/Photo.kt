package com.tapok.dalimopromotions.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("path_url") val pathUrl: String
) : Parcelable