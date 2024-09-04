package com.example.test.di

import com.example.test.domain.api.MainApi
import com.example.test.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(mainApi: MainApi): MainRepository {
        return MainRepository(mainApi)
    }
}