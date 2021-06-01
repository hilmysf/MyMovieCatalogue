package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<List<MovieResponse>>


    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getPopularMovies(){
        val movies = MutableLiveData<List<MovieResponse>>()
        movies.postValue(dummyMovie)
        Mockito.`when`(catalogueRepository.getPopularMovies()).thenReturn(movies)
        val movieLists = viewModel.getPopularMovie().value
        verify(catalogueRepository).getPopularMovies()
        assertNotNull(movieLists)
        viewModel.getPopularMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)
    }
//    @Before
//    fun setUp() {
//        viewModel = MovieViewModel()
//    }
//
//    @Test
//    fun testGetMoviesList() {
//        val movieEntities = viewModel.getMoviesList()
//        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities.size)
//    }
}