package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mymoviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShowsList(): List<MovieEntity> = DataDummy.generateDummyTvShows()
}