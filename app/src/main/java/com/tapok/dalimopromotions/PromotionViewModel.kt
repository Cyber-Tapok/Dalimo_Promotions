package com.tapok.dalimopromotions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.model.PromotionRepository
import kotlinx.coroutines.launch


class PromotionViewModel(private val promotionRepository: PromotionRepository) : ViewModel() {

    val issues = promotionRepository.loadData()

    fun updateData() {
        viewModelScope.launch {
            promotionRepository.updateData()
        }
    }

    val selected = MutableLiveData<Promotion?>()

    fun select(item: Promotion) {
        selected.value = item
    }


}