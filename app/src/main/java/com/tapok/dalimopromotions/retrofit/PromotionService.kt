package com.tapok.dalimopromotions.retrofit

import com.tapok.dalimopromotions.model.PromotionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionService {
    @GET("promotions/")
    suspend fun getPromotionFromApi( @Query("page") page: Int): PromotionResponse
}