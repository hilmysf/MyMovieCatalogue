package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val dummyTvShow = DataDummy.generateDummyDetailTv()
    private val movieId = dummyMovie.movieId
    private val tvId = dummyTvShow.tvId

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieResponse>

    @Mock
    private lateinit var tvObserver: Observer<TvResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val movies = MutableLiveData<MovieResponse>()

        movies.postValue(dummyMovie)
        `when`(catalogueRepository.getMovieDetail(movieId!!)).thenReturn(movies)
        val movieEntity = viewModel.getDetailMovie(movieId).value

        assertNotNull(movieEntity)
        viewModel.getDetailMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tv = MutableLiveData<TvResponse>()

        tv.postValue(dummyTvShow)
        `when`(catalogueRepository.getTvShowDetail(tvId!!)).thenReturn(tv)
        val tvEntity = viewModel.getDetailTv(tvId).value

        assertNotNull(tvEntity)
        viewModel.getDetailTv(tvId).observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShow)
    }
}