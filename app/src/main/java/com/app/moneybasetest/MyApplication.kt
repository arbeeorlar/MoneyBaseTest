package com.app.moneybasetest

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}

