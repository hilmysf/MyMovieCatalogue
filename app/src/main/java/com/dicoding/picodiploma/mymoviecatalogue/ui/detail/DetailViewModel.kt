package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movieId: String
    fun setMovieList(movieId: String) {
        this.movieId = movieId
    }

    fun getMoviesList(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvList(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyTvShows()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }
}