package com.tapok.dalimopromotions.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tapok.dalimopromotions.database.PromotionDao
//import com.tapok.dalimopromotions.database.PromotionDao
import com.tapok.dalimopromotions.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromotionRepository @Inject constructor(private val promotionDao: PromotionDao) {

    suspend fun updateData() {
        withContext(Dispatchers.IO) {
            val promotionResponse = RetrofitClient.getService().getPromotionFromApi()
            Log.e("TEST", promotionResponse.data.size.toString())
            Log.e("TEST", "da")
            promotionDao.updateData(promotionResponse.data)
        }
    }

    fun loadData() : LiveData<List<Promotion>> {
        return promotionDao.getData()
    }

}