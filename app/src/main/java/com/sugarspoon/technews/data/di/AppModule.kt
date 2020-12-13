package com.sugarspoon.technews.data.di

import com.sugarspoon.technews.data.remote.datasource.NewsDataSource
import com.sugarspoon.technews.data.remote.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(newsDataSource: NewsDataSource): NewsRepositoryInterface
}