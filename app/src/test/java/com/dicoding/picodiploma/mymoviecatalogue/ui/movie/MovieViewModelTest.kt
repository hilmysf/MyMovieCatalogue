package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
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

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getPopularMovies(){
        val moviesList = MutableLiveData<Resource<List<MovieEntity>>>()
        Mockito.`when`(viewModel.getPopularMovie()).thenReturn(moviesList)
        catalogueRepository.getPopularMovies()

        val movieEntities = Resource.success(DataDummy.generateDummyMovies())
        assertNotNull(movieEntities.body)
        assertEquals(dummyMovie.size.toLong(), movieEntities.body?.size?.toLong())
    }
}