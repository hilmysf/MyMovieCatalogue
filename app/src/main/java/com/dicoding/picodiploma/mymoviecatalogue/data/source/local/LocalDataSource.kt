package com.dicoding.picodiploma.mymoviecatalogue.data.source.local

import androidx.paging.DataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.room.CatalogueDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mCatalogueDao: CatalogueDao) {

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getFavMovies()

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getFavTvShows()

    suspend fun insertFavMovie(movie: MovieEntity) = mCatalogueDao.insertFavMovie(movie)

    suspend fun insertFavTvShow(tvShow: TvShowEntity) = mCatalogueDao.insertFavTvShow(tvShow)

    suspend fun deleteFavMovie(movie: MovieEntity) = mCatalogueDao.deleteFavMovie(movie)

    suspend fun deleteFavTvShow(tvShow: TvShowEntity) = mCatalogueDao.deleteFavTvShow(tvShow)

    suspend fun getFavMovieById(movie: MovieEntity?) = mCatalogueDao.getFavMovieById(movie?.movieId) != null

    suspend fun getFavTvShowById(tvShow: TvShowEntity?) = mCatalogueDao.getFavTvShowById(tvShow?.tvId) != null

}