package com.sugarspoon.technews.ui.news.routes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class StateNewsRoutes(
    private val activity: AppCompatActivity,
    private val routeWebView: Intent
) {

    fun openWebView() {
        activity.startActivity(routeWebView)
    }
}