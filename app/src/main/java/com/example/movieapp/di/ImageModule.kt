package com.example.movieapp.di

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.ViewTarget
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    /*
    @Provides
    @Singleton
    internal fun provideGlide(context: Context, path: String, imageView: ImageView) {
        Glide.with(context)
        .load(path)
        .transform(CenterCrop())
        .into(imageView)
    }

     */
}