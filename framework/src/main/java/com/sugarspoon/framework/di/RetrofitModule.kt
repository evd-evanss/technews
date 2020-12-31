package com.sugarspoon.framework.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.sugarspoon.data.NetworkConstants.API_KEY
import com.sugarspoon.technews.BuildConfig
import com.sugarspoon.data.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun retrofit(@ApplicationContext context: Context): Retrofit.Builder {
        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor(ChuckInterceptor(context))
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                    val originalHttpUrl = chain.request().url
                    val url = originalHttpUrl.newBuilder().addQueryParameter(
                        API_KEY,
                        BuildConfig.NEWS_API_KEY
                    ).build()
                    request.url(url)
                    return@addInterceptor chain.proceed(request.build())
                }
                .addInterceptor(ErrorInterceptor())
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
    }
}
