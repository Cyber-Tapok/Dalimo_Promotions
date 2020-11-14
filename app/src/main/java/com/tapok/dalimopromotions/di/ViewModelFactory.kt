package com.tapok.dalimopromotions.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapok.dalimopromotions.PromotionViewModel
import com.tapok.dalimopromotions.model.PromotionRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelFactory @Inject constructor(private val promotionRepository: PromotionRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PromotionViewModel(promotionRepository) as T
    }
}