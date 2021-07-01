package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = Resource.success(DataDummy.generateDummyDetailMovie())
    private val dummyTvShow = Resource.success(DataDummy.generateDummyDetailTv())
    private val movieId = dummyMovie.body?.movieId
    private val tvId = dummyTvShow.body?.tvId
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieResponse>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvResponse>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val movies = MutableLiveData<Resource<MovieResponse>>()

        movies.postValue(dummyMovie)
        `when`(catalogueRepository.getMovieDetail(movieId)).thenReturn(movies)
        val movieEntity = viewModel.getDetailMovie(movieId).value

        assertNotNull(movieEntity)
        viewModel.getDetailMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tv = MutableLiveData<Resource<TvResponse>>()

        tv.postValue(dummyTvShow)
        `when`(catalogueRepository.getTvShowDetail(tvId)).thenReturn(tv)
        val tvEntity = viewModel.getDetailTv(tvId).value

        assertNotNull(tvEntity)
        viewModel.getDetailTv(tvId).observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShow)
    }

    @Test
    fun insertFavMovie(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(viewModel).insertFavMovie(favoriteMovieItem)
            viewModel.insertFavMovie(favoriteMovieItem)

            verify(viewModel).insertFavMovie(favoriteMovieItem)
        }
    }

    @Test
    fun insertFavTvShow(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(
                10,
                "Zombie Keramas",
                "10-03-00",
                8.0,
                "Film bagus lumayan",
                "sfamkf3rno",
                "dsmkdsfn4",
                "10-10-00",
                false
            )
            doNothing().`when`(viewModel).insertFavTvShow(favoriteTvShowItem)
            catalogueRepository.insertFavTvShow(favoriteTvShowItem)
            verify(viewModel).insertFavTvShow(favoriteTvShowItem)
        }
    }

    @Test
    fun deleteFavMovie(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(viewModel).deleteFavMovie(favoriteMovieItem)
            catalogueRepository.deleteFavMovie(favoriteMovieItem)
            verify(viewModel).deleteFavMovie(favoriteMovieItem)
        }
    }

    @Test
    fun deleteFavTvShow(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(viewModel).deleteFavTvShow(favoriteTvShowItem)
            catalogueRepository.deleteFavTvShow(favoriteTvShowItem)
            verify(viewModel).deleteFavTvShow(favoriteTvShowItem)
        }
    }

    @Test
    fun isFavMovie(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(
                10,
                "Suster Keramas",
                "10-03-00",
                9.0,
                "Film bagus",
                "sfamkf3rno",
                "dsmkdsfn4",
                true
            )
            doNothing().`when`(viewModel).isFavoritedMovie(favoriteMovieItem)
            catalogueRepository.isFavoritedMovie(favoriteMovieItem)
            com.nhaarman.mockitokotlin2.verify(viewModel).isFavoritedMovie(favoriteMovieItem)
        }
    }

    @Test
    fun isFavTvShow(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(viewModel).isFavoritedTvShow(favoriteTvShowItem)
            catalogueRepository.isFavoritedTvShow(favoriteTvShowItem)
            verify(viewModel).isFavoritedTvShow(favoriteTvShowItem)
        }
    }
}