package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieIdM = dummyMovie.movieId
    private val movieIdT = dummyTvShow.movieId

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
        viewModel.setMovieList(movieIdM)
        viewModel.setMovieList(movieIdT)
    }

    @Test
    fun testGetMoviesList() {
        viewModel.setMovieList(dummyMovie.movieId)
        val movieEntity = viewModel.getMoviesList()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.creator, movieEntity.creator)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.score, movieEntity.score)
        assertEquals(dummyMovie.title, movieEntity.title)
    }

    @Test
    fun testGetTvList() {
        viewModel.setMovieList(dummyTvShow.movieId)
        val tvShowEntity = viewModel.getTvList()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.movieId, tvShowEntity.movieId)
        assertEquals(dummyTvShow.poster, tvShowEntity.poster)
        assertEquals(dummyTvShow.creator, tvShowEntity.creator)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.score, tvShowEntity.score)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
    }
}