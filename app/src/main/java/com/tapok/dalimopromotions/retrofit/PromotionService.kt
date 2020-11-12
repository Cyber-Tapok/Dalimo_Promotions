package com.tapok.dalimopromotions.retrofit

import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.model.ResponceApi
import retrofit2.Call
import retrofit2.http.GET

interface PromotionService {
    @GET("?page=1")
    fun issueCall(): Call<ResponceApi>
}