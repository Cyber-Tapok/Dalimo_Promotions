package com.tapok.dalimopromotions.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "promotions")
data class Promotion(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("cover") val coverUrl: String?,
    @Embedded(prefix = "photo") @SerializedName("photo") val photo: Photo?
) : Parcelable