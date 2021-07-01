package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.nhaarman.mockitokotlin2.doNothing
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavViewModel(catalogueRepository)
    }

    @Test
    fun getFavMoviesTest(){
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(catalogueRepository.getFavMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavMovies().value
        verify(catalogueRepository).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)
    }

    @Test
    fun getFavTvShowTest(){
        val dummyTvShow = tvPagedList
        `when`(dummyTvShow.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TvShowEntity>>()
        tv.value = dummyTvShow

        `when`(catalogueRepository.getFavTvShows()).thenReturn(tv)
        val tvShowEntities = viewModel.getFavTvShows().value
        verify(catalogueRepository).getFavTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)
    }

    @Test
    fun deleteFavMovieTest(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(viewModel).deleteFavMovie(favoriteMovieItem)
            catalogueRepository.deleteFavMovie(favoriteMovieItem)
            verify(viewModel).deleteFavMovie(favoriteMovieItem)
        }
    }

    @Test
    fun deleteFavTvShowTest(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(viewModel).deleteFavTvShow(favoriteTvShowItem)
            catalogueRepository.deleteFavTvShow(favoriteTvShowItem)
            verify(viewModel).deleteFavTvShow(favoriteTvShowItem)
        }
    }

    @Test
    fun insertFavMoviesTest(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(viewModel).insertFavMovie(favoriteMovieItem)
            catalogueRepository.insertFavMovie(favoriteMovieItem)
            verify(viewModel).insertFavMovie(favoriteMovieItem)
        }
    }


    @Test
    fun insertFavTvShowTest(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(viewModel).insertFavTvShow(favoriteTvShowItem)
            catalogueRepository.insertFavTvShow(favoriteTvShowItem)
            verify(viewModel).insertFavTvShow(favoriteTvShowItem)
        }
    }
}