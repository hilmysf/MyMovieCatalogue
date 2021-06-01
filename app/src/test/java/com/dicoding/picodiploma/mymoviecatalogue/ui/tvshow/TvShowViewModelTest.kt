package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTv = DataDummy.generateDummyTv()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var tvObserver: Observer<List<TvResponse>>


    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getPopularTv(){
        val tv = MutableLiveData<List<TvResponse>>()
        tv.postValue(dummyTv)
        Mockito.`when`(catalogueRepository.getPopularTvShow()).thenReturn(tv)
        val tvLists = viewModel.getPopularTv().value
        verify(catalogueRepository).getPopularTvShow()
        assertNotNull(tvLists)
        viewModel.getPopularTv().observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTv)
    }
}