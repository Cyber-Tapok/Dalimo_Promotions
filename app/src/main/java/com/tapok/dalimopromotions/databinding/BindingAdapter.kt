package com.tapok.dalimopromotions.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:url", "app:errorImage")
fun loadImage(view: ImageView, url: String?, errorImage: Drawable) {
    if (!url.isNullOrEmpty()) {
        Picasso.get().load("https:$url").error(errorImage).fit().centerCrop().into(view)
    }
}