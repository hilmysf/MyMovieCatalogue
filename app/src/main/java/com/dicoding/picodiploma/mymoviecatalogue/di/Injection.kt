package com.dicoding.picodiploma.mymoviecatalogue.di

import android.content.Context
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.ApiConfig
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mymoviecatalogue.utils.JsonHelper

//object Injection {
//    fun provideRepository(context: Context): CatalogueRepository{
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig)
//        return (CatalogueRepository.getInstance(remoteDataSource) as CatalogueRepository?)!!
//    }
//}