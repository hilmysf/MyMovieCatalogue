package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getFavMovies(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavMovies()

    fun getFavTvShows(): LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavTvShows()

    fun deleteFavMovie(movie: MovieEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.deleteFavMovie(movie) }

    fun deleteFavTvShow(tv: TvShowEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.deleteFavTvShow(tv) }

    fun insertFavMovie(movie: MovieEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.insertFavMovie(movie) }

    fun insertFavTvShow(tv: TvShowEntity) =
        viewModelScope.launch(Dispatchers.IO) { catalogueRepository.insertFavTvShow(tv) }

}