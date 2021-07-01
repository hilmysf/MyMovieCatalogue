package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource

class MovieViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getPopularMovie(): LiveData<Resource<List<MovieEntity>>> = catalogueRepository.getPopularMovies()
}