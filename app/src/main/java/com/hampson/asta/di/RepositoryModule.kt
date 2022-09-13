package com.hampson.asta.di

import com.hampson.asta.data.repository.ConnectRepositoryImpl
import com.hampson.asta.domain.repository.ConnectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun binConnectRepository(
        connectRepositoryImpl: ConnectRepositoryImpl,
    ): ConnectRepository
}