package com.sugarspoon.technews.framework.di

import com.sugarspoon.data.NewsDataSource
import com.sugarspoon.technews.framework.repository.NewsDataRepoImp
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
    abstract fun bindNewsDataSource(newsDataRepoImp: NewsDataRepoImp): NewsDataSource
}