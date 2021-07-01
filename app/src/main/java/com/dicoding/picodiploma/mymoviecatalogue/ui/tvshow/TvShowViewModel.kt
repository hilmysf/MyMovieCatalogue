package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource

class TvShowViewModel @ViewModelInject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getPopularTv(): LiveData<Resource<List<TvShowEntity>>> = catalogueRepository.getPopularTvShow()
}
