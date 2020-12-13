package com.sugarspoon.technews.data.remote.repository

import retrofit2.Retrofit

internal class RetrofitServiceFactory(
    val retrofitBuilder: Retrofit.Builder
) {

    inline fun <reified Service> newInstance(): Service {
        return retrofitBuilder
            .build()
            .create(Service::class.java)
    }
}
