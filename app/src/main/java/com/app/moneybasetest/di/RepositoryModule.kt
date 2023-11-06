package com.app.moneybasetest.di

import com.app.moneybasetest.data.datascource.remote.ApiService
import com.app.moneybasetest.data.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideStockRepository(
        apiService: ApiService,
    ): StockRepository {
        return StockRepository(
            apiService
        )
    }

}