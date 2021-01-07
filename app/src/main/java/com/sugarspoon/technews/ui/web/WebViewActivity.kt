package com.sugarspoon.technews.ui.web

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sugarspoon.technews.R
import kotlinx.android.synthetic.main.activity_web_view.*
import org.jetbrains.anko.intentFor

class WebViewActivity : AppCompatActivity() {

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        getExtras()
        loadWebView()
        enableHtml5()
    }

    private fun getExtras() {
        url = intent.getStringExtra("EXTRA_URL") as String
    }

    private fun loadWebView() {
        webview.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        webview.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun enableHtml5() {
        webview.settings.javaScriptEnabled = true
        webview.settings.allowFileAccess = true
    }
}

fun Context.webViewIntent(url: String) =
    intentFor<WebViewActivity>("EXTRA_URL" to url)