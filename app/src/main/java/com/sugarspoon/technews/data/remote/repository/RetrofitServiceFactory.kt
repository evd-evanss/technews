package com.sugarspoon.technews.data.remote.repository

import com.sugarspoon.technews.BuildConfig.BASE_URL
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitServiceFactory(
    val retrofitBuilder: Retrofit.Builder
) {

    inline fun <reified Service> newInstance(): Service {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }
}
