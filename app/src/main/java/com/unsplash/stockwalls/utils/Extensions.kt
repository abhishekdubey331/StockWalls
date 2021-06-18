package com.unsplash.stockwalls.utils

import android.widget.Toast
import com.unsplash.stockwalls.HiltSample


fun String.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(HiltSample.context, this, duration).apply { show() }
}