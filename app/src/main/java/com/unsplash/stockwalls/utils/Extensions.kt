package com.unsplash.stockwalls.utils

import android.widget.Toast
import com.unsplash.stockwalls.StockWallsApp


fun String.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(StockWallsApp.context, this, duration).apply { show() }
}