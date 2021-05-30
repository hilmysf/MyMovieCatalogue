package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MovieViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val getPopularMovie: LiveData<List<MovieResponse>> = catalogueRepository.getPopularMovies()
    }