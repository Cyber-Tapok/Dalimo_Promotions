package com.tapok.dalimopromotions.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tapok.dalimopromotions.R

@BindingAdapter("app:url", "app:supportImage")
fun loadImage(view: ImageView, url: String?, supportImage: Drawable) {
    if (!url.isNullOrEmpty()) {
        Picasso.get().load("https://img.dalimo.ru/300$url").placeholder(supportImage).error(supportImage).into(view)
    } else view.setImageResource(R.drawable.noimg_190x160)
}