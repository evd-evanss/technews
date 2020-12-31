package com.sugarspoon.technews.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sugarspoon.technews.utils.NetworkErrorListener
import com.sugarspoon.technews.R
import com.sugarspoon.technews.utils.extensions.onCollect
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startErrorListener()
    }

    private fun startErrorListener(){
        val eventError = NetworkErrorListener.getEvents()
        eventError.run {
            code.onCollect(
                onSuccess = { code ->
                    code?.let { toast("code: $it") }
                }
            )
        }
    }
}
