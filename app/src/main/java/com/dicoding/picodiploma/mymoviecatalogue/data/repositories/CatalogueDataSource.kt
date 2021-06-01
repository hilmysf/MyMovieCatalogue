package com.dicoding.picodiploma.mymoviecatalogue.data.repositories

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*

interface CatalogueDataSource {
    fun getPopularMovies(): LiveData<List<MovieResponse>>
    fun getMovieDetail(movieId: Int): LiveData<MovieResponse>
    fun getPopularTvShow(): LiveData<List<TvResponse>>
    fun getTvShowDetail(tvShowId: Int): LiveData<TvResponse>
}