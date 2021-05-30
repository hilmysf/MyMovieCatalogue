package com.dicoding.picodiploma.mymoviecatalogue.data.repositories

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface CatalogueDataSource {

    fun getPopularMovies(): LiveData<List<MovieResponse>>
    fun getMovieDetail(movieId: Int): LiveData<MovieResponse>
    fun getPopularTvShow(): LiveData<List<TvResponse>>
    fun getTvShowDetail(tvShowId: Int): LiveData<TvResponse>

}