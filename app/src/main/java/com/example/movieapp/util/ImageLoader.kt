package com.example.movieapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

object ImageLoader {
    fun provideGlide(context: Context, path: String, imageView: ImageView) =
        Glide.with(context)
            .load(path)
            .transform(CenterCrop())
            .into(imageView)
}