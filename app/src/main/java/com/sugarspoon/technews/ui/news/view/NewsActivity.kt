package com.sugarspoon.technews.ui.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sugarspoon.technews.R
import dagger.hilt.android.AndroidEntryPoint

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
