package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.repositories.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTv = DataDummy.generateDummyTv()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getPopularTv() {
        val tvShowList = MutableLiveData<Resource<List<TvShowEntity>>>()
        Mockito.`when`(viewModel.getPopularTv()).thenReturn(tvShowList)
        catalogueRepository.getPopularTvShow()

        val tvShowEntities = Resource.success(DataDummy.generateDummyTv())
        assertNotNull(tvShowEntities.body)
        assertEquals(dummyTv.size.toLong(), tvShowEntities.body?.size?.toLong())
    }
}