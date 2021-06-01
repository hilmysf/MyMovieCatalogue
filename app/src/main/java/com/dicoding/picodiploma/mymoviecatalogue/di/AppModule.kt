package com.dicoding.picodiploma.mymoviecatalogue.di

import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.MovieApiService
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun providesRepo(remoteDataSource: RemoteDataSource): CatalogueRepository =
        CatalogueRepository(remoteDataSource)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: MovieApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideApi(): MovieApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApiService::class.java)
}