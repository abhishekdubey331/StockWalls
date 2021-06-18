package com.unsplash.stockwalls

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltSample : Application() {

    companion object {
        lateinit var application: HiltSample

        val context: Context
            get() = application.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
