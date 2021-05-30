package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val getPopularTv: LiveData<List<TvResponse>> = catalogueRepository.getPopularTvShow()
   }
