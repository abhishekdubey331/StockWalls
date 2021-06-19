package com.unsplash.stockwalls.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.StockWallsApp
import com.unsplash.stockwalls.view.detail.FullPhotoActivity

fun String.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(StockWallsApp.context, this, duration).apply { show() }
}

fun AppCompatImageView.loadImageWithPlaceholder(imageUrl: String) {
    Glide.with(StockWallsApp.context).load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(StockWallsApp.context).load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun <T> Context.openActivity(
    it: Class<T>,
    options: ActivityOptionsCompat,
    extras: Bundle.() -> Unit = {},
) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent, options.toBundle())
}

fun FullPhotoActivity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        this.window.statusBarColor = Color.TRANSPARENT
    } else {
        this.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}