package com.tapok.dalimopromotions.retrofit

import com.tapok.dalimopromotions.model.PromotionResponse
import retrofit2.Call
import retrofit2.http.GET

interface PromotionService {
    @GET("?page=2")
    suspend fun getPromotionFromApi(): PromotionResponse
}