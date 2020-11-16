package com.tapok.dalimopromotions.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "promotions")
data class Promotion(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("text") val text: String?,
    @Embedded(prefix = "photo") @SerializedName("photo") val photo: Photo?
)