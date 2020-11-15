package com.tapok.dalimopromotions.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.R
import com.tapok.dalimopromotions.databinding.ItemPromotionBinding
import com.tapok.dalimopromotions.model.Promotion

class ItemPromotionAdapter :
    RecyclerView.Adapter<ItemPromotionAdapter.ItemPromotionViewHolder>() {

    private var listPromotion: List<Promotion> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPromotionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPromotionBinding>(
            layoutInflater,
            R.layout.item_promotion,
            parent,
            false
        )
        return ItemPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemPromotionViewHolder, position: Int) {
        holder.bind(listPromotion[position])
    }

    override fun getItemCount(): Int {
        return listPromotion.size
    }

    fun setData(listPromotion: List<Promotion>) {
        this.listPromotion = listPromotion
        notifyDataSetChanged()
    }

    fun updataData(listPromotion: List<Promotion>) {
        val diffUtilCallBack = PromotionDiffUtil(this.listPromotion, listPromotion)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack, false)
        this.listPromotion = listPromotion
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ItemPromotionViewHolder(private val binding: ItemPromotionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(promotion: Promotion) {
            binding.promotion = promotion
        }
    }
}