package com.dicoding.picodiploma.mymoviecatalogue.di

import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.ApiConfig
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRepo(apiConfig: ApiConfig): CatalogueRepository{
        return CatalogueRepository(RemoteDataSource.getInstance(apiConfig))
    }
}