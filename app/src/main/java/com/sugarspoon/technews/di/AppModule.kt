package com.sugarspoon.technews.di

import com.sugarspoon.data.NewsDataSource
import com.sugarspoon.data.NewsDataSourceRepository
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
    abstract fun bindNewsRepository(newsRepository: NewsDataSourceRepository): NewsDataSource
}