package com.dicoding.picodiploma.mymoviecatalogue.di

import android.content.Context
import androidx.room.Room
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.LocalDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.room.CatalogueDao
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.room.CatalogueDatabase
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.MovieApiService
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesRepo(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): CatalogueRepository =
        CatalogueRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: MovieApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideLocalDataSource(mCatalogueDao: CatalogueDao): LocalDataSource =
        LocalDataSource(mCatalogueDao)

    @Singleton
    @Provides
    fun provideApi(): MovieApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideDao(db: CatalogueDatabase) = db.catalogueDao()

    @Singleton
    @Provides
    fun provideCatalogueDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CatalogueDatabase::class.java,
        "catalogue.db"
    ).build()
}