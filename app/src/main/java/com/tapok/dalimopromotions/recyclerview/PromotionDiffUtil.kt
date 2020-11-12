package com.tapok.dalimopromotions.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.tapok.dalimopromotions.model.Promotion

class PromotionDiffUtil(private val oldData: List<Promotion>, private val newData: List<Promotion>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPromotion = oldData[oldItemPosition]
        val newPromotion = newData[newItemPosition]
        return oldPromotion.id == newPromotion.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPromotion = oldData[oldItemPosition]
        val newPromotion = newData[newItemPosition]
        return oldPromotion.text == newPromotion.text && oldPromotion.title == newPromotion.title
    }
}