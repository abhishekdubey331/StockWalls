package com.unsplash.stockwalls.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.size.Scale
import com.unsplash.stockwalls.StockWallsApp

fun String.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(StockWallsApp.context, this, duration).apply { show() }
}

fun AppCompatImageView.loadImage(imageUrl: String, fastLoadUrl: String) {
    this.load(imageUrl) {
       scale(Scale.FIT)
    }
}

fun AppCompatImageView.loadFullImage(imageUrl: String, fastLoadUrl: String) {
    this.load(imageUrl)
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}