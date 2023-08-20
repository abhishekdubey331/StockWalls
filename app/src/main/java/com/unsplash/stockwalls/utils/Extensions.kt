package com.unsplash.stockwalls.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.view.detail.FullPhotoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/***
 *  Set the View visibility to visible
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/***
 *  Set the View visibility to gone
 */
fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatImageView.loadImage(imageUrl: String) {
    load(imageUrl)
}

fun AppCompatImageView.loadImageWithPlaceholder(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.placeholder)
    }
}

/**
 * Launches a new coroutine and repeats `block` every time the Fragment's viewLifecycleOwner
 * is in and out of `minActiveState` lifecycle state.
 */
inline fun AppCompatActivity.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

inline fun <T> Context.openActivity(
    it: Class<T>,
    options: ActivityOptionsCompat,
    extras: Bundle.() -> Unit = {},
) {
    val intent = Intent()
    intent.setClass(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent, options.toBundle())
}

fun FullPhotoActivity.transparentStatusBar() {
    this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    this.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    this.window.statusBarColor = Color.TRANSPARENT
}