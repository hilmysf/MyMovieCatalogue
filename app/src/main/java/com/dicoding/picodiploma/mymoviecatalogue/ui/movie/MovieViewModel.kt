package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse

class MovieViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getPopularMovie(): LiveData<List<MovieResponse>> = catalogueRepository.getPopularMovies()
}