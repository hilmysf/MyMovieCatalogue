package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getDetailMovie(movieId: Int?): LiveData<Resource<MovieResponse>> =
        catalogueRepository.getMovieDetail(movieId)

    fun getDetailTv(tvId: Int?): LiveData<Resource<TvResponse>> =
        catalogueRepository.getTvShowDetail(tvId)

    fun insertFavMovie(movie: MovieEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.insertFavMovie(movie) }

    fun insertFavTvShow(tv: TvShowEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.insertFavTvShow(tv) }

    fun deleteFavMovie(movie: MovieEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.deleteFavMovie(movie) }

    fun deleteFavTvShow(tv: TvShowEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.deleteFavTvShow(tv) }

    fun isFavoritedMovie(movie: MovieEntity?): Boolean =
        runBlocking { catalogueRepository.isFavoritedMovie(movie) }

    fun isFavoritedTvShow(tv: TvShowEntity?): Boolean =
        runBlocking { catalogueRepository.isFavoritedTvShow(tv) }

}