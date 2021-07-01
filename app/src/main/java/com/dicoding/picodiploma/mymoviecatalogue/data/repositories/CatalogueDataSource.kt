package com.dicoding.picodiploma.mymoviecatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularTvShow(): LiveData<Resource<List<TvShowEntity>>>

    fun getMovieDetail(movieId: Int?): LiveData<Resource<MovieResponse>>

    fun getTvShowDetail(tvShowId: Int?): LiveData<Resource<TvResponse>>

    fun getFavMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavTvShows(): LiveData<PagedList<TvShowEntity>>

    suspend fun insertFavMovie(movie: MovieEntity)

    suspend fun insertFavTvShow(tvShow: TvShowEntity)

    suspend fun deleteFavMovie(movie: MovieEntity)

    suspend fun deleteFavTvShow(tvShow: TvShowEntity)

    suspend fun isFavoritedMovie(movie: MovieEntity?): Boolean

    suspend fun isFavoritedTvShow(tvShow: TvShowEntity?): Boolean
}