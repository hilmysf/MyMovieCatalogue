package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetMoviesList() {
        val movieEntities = viewModel.getMoviesList()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}