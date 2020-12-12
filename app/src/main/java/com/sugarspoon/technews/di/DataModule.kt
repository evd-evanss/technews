package com.sugarspoon.technews.di

import com.sugarspoon.technews.data.datasource.NewsApiDataSource
import com.sugarspoon.technews.data.datasource.NewsRetrofitApiDataSource
import com.sugarspoon.technews.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) // Essa anotação referencia o modulo ao ciclo de vida da aplicação
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(
        newsRepository: NewsRepository
    ): NewsRepositoryInterface

    @Singleton
    @Binds
    abstract fun bindApiDataSource(
        apiDataSource: NewsRetrofitApiDataSource
    ): NewsApiDataSource
}