package com.sugarspoon.technews.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sugarspoon.technews.R
import com.sugarspoon.data.NetworkErrorListener
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
        NetworkErrorListener.getEvents().onCollect(
            onSuccess = {
               if(it){
                   toast("Erro 401")
               }
            }
        )
    }
}
