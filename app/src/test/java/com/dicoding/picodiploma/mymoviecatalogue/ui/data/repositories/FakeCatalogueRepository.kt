package com.dicoding.picodiploma.mymoviecatalogue.ui.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.LocalDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import javax.inject.Inject

class FakeCatalogueRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    CatalogueDataSource {


    override fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> = remoteDataSource.getPopularMovies()

    override fun getMovieDetail(movieId: Int?): LiveData<Resource<MovieResponse>> = remoteDataSource.getDetailMovie(movieId)

    override fun getPopularTvShow(): LiveData<Resource<List<TvShowEntity>>> = remoteDataSource.getPopularTv()

    override fun getTvShowDetail(tvShowId: Int?): LiveData<Resource<TvResponse>> = remoteDataSource.getDetailTvShow(tvShowId)

    override fun getFavMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getFavTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override suspend fun insertFavMovie(movie: MovieEntity) = localDataSource.insertFavMovie(movie)

    override suspend fun insertFavTvShow(tvShow: TvShowEntity) = localDataSource.insertFavTvShow(tvShow)

    override suspend fun deleteFavMovie(movie: MovieEntity) = localDataSource.deleteFavMovie(movie)

    override suspend fun deleteFavTvShow(tvShow: TvShowEntity) = localDataSource.deleteFavTvShow(tvShow)

    override suspend fun isFavoritedMovie(movie: MovieEntity?): Boolean = localDataSource.getFavMovieById(movie)

    override suspend fun isFavoritedTvShow(tvShow: TvShowEntity?): Boolean = localDataSource.getFavTvShowById(tvShow)
}