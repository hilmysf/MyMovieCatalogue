package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetMoviesList() {
        val tvEntities = viewModel.getTvShowsList()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities.size)
    }
}