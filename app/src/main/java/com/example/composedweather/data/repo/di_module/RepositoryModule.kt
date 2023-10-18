package com.example.composedweather.data.repo.di_module

import com.example.composedweather.data.remote.WeatherRemoteDataSource
import com.example.composedweather.data.repo.contract.WeatherRepository
import com.example.composedweather.data.repo.implementation.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }

}