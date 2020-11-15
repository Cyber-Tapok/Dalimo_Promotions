package com.tapok.dalimopromotions

import com.tapok.dalimopromotions.model.Promotion

sealed class DataState {
    object Idle : DataState()
    data class Success(val dataList: List<Promotion>): DataState()
    data class Failed(val dataList: List<Promotion>): DataState()
    object Empty: DataState()
}