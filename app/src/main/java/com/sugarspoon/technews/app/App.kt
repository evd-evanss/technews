package com.sugarspoon.technews.app

import android.app.Application
import com.androidnetworking.AndroidNetworking
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}


