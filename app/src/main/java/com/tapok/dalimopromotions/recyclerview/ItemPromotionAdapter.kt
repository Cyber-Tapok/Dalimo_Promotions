package com.tapok.dalimopromotions.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.databinding.ItemPromotionBinding
import com.tapok.dalimopromotions.model.Promotion

class ItemPromotionAdapter :
    RecyclerView.Adapter<ItemPromotionAdapter.ItemPromotionViewHolder>() {
    private var listPromotion: List<Promotion> = emptyList()
    var selectedItemId: Int = -1
    var clickListener: ((promotion: Promotion) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPromotionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPromotionBinding.inflate(layoutInflater, parent, false)
        return ItemPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemPromotionViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listPromotion.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
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

    fun resetSelectItem() {
        val position = selectedItemId
        selectedItemId = -1
        notifyItemChanged(position)
    }

    inner class ItemPromotionViewHolder(private val binding: ItemPromotionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                notifyItemChanged(selectedItemId)
                selectedItemId = adapterPosition
                clickListener?.invoke(listPromotion[adapterPosition])
                notifyItemChanged(selectedItemId)
            }
        }

        fun bind(position: Int) {
            binding.promotion = listPromotion[position]
            itemView.isSelected = selectedItemId == position
        }
    }
}