package ru.magzyumov.material.common

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideLoader {
    fun loadImage(view: ImageView, image: String) {
        Glide.with(view.context)
            .load(Uri.parse(image))
            .centerCrop()
            .fitCenter()
            .into(view)
    }
}