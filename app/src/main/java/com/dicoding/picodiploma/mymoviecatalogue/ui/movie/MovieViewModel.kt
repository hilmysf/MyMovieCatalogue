package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy

class MovieViewModel: ViewModel() {
    fun getMoviesList(): List<MovieEntity> = DataDummy.generateDummyMovies()
}