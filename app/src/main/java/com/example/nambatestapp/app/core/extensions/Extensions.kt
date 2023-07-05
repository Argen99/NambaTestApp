package com.example.nambatestapp.app.core.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun Int.toPx(context: Context) =
    this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
