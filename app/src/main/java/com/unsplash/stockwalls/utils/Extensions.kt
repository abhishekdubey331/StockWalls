package com.unsplash.stockwalls.utils

import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.unsplash.stockwalls.StockWallsApp


fun String.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(StockWallsApp.context, this, duration).apply { show() }
}

fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide
        .with(StockWallsApp.context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}