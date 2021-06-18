package com.unsplash.stockwalls

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltSample : Application() {

    companion object {
        lateinit var application: HiltSample

        fun context() = application.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
