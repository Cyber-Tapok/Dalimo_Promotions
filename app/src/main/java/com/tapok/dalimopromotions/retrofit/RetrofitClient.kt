package com.tapok.dalimopromotions.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.dalimo.ru/api/v1/promotions/"

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): PromotionService {
        return retrofit.create(PromotionService::class.java)
    }
}