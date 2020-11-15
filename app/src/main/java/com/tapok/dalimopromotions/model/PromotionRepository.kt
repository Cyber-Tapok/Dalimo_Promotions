package com.tapok.dalimopromotions.model

//import com.tapok.dalimopromotions.database.PromotionDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tapok.dalimopromotions.DataState
import com.tapok.dalimopromotions.database.PromotionDao
import com.tapok.dalimopromotions.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromotionRepository @Inject constructor(private val promotionDao: PromotionDao) {

    private val state: MutableLiveData<DataState> = MutableLiveData(DataState.Idle)
    private var isRequestStart: Boolean = false

    suspend fun updateData() {
        if (!isRequestStart) {
            isRequestStart = true
            state.postValue(DataState.Idle)
            withContext(Dispatchers.IO) {
                try {
                    val promotionResponse = RetrofitClient.getService().getPromotionFromApi().data
                    promotionDao.updateData(promotionResponse)
                    state.postValue(
                        if (promotionResponse.isEmpty()) DataState.Empty else DataState.Success(
                            loadData()
                        )
                    )
                } catch (e: Exception) {
                    state.postValue(DataState.Failed(loadData()))
                }
                isRequestStart = false
            }
        }
    }

    private fun loadData(): List<Promotion> {
        return promotionDao.getData()
    }

    fun getCurrentStatus(): LiveData<DataState> {
        return state
    }

}