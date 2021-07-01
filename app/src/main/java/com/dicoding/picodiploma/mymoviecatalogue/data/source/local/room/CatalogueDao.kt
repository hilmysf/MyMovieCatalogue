package com.dicoding.picodiploma.mymoviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity

@Dao
interface CatalogueDao{

    @Query("SELECT * FROM movieentities")
    fun getPopularMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities")
    fun getPopularTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    suspend fun getFavMovieById(movieId: Int?): MovieEntity?

    @Query("SELECT * FROM tvshowentities WHERE tvId = :tvId")
    suspend fun getFavTvShowById(tvId: Int?): TvShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTvShow(tvShow: TvShowEntity)

    @Delete
    suspend fun deleteFavMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavTvShow(tvShow: TvShowEntity)
}