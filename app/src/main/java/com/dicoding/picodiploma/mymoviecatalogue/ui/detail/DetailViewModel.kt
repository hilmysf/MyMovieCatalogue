package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse

class DetailViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getDetailMovie(movieId: Int): LiveData<MovieResponse> =
        catalogueRepository.getMovieDetail(movieId)

    fun getDetailTv(tvId: Int): LiveData<TvResponse> = catalogueRepository.getTvShowDetail(tvId)

}