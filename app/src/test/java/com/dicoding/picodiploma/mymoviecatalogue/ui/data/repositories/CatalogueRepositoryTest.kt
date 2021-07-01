package com.dicoding.picodiploma.mymoviecatalogue.ui.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.LocalDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.ui.utils.PagedListUtil
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class CatalogueRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private val catalogueRepository = FakeCatalogueRepository(remote, local)

    private val dummyDetailMovie = Resource.success(DataDummy.generateDummyDetailMovie())
    private val dummyDetailTvShow = Resource.success(DataDummy.generateDummyDetailTv())
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTv = DataDummy.generateDummyTv()
    private val movieId = dummyDetailMovie.body?.movieId
    private val tvId = dummyDetailTvShow.body?.tvId

    @Test
    fun getPopularMovies() {
        val moviesList = MutableLiveData<Resource<List<MovieEntity>>>()
        `when`(remote.getPopularMovies()).thenReturn(moviesList)
        catalogueRepository.getPopularMovies()

        val movieEntities = Resource.success(DataDummy.generateDummyMovies())
        verify(remote).getPopularMovies()
        assertNotNull(movieEntities.body)
        assertEquals(dummyMovie.size.toLong(), movieEntities.body?.size?.toLong())
    }

    @Test
    fun getPopularTvShow() {
        val tvShowList = MutableLiveData<Resource<List<TvShowEntity>>>()
        `when`(remote.getPopularTv()).thenReturn(tvShowList)
        catalogueRepository.getPopularTvShow()

        val tvShowEntities = Resource.success(DataDummy.generateDummyTv())
        verify(remote).getPopularTv()
        assertNotNull(tvShowEntities.body)
        assertEquals(dummyTv.size.toLong(), tvShowEntities.body?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Resource<MovieResponse>>()
        movie.postValue(dummyDetailMovie)
        `when`(remote.getDetailMovie(movieId)).thenReturn(movie)

        val movieEntities = catalogueRepository.getMovieDetail(movieId).value
        verify(remote).getDetailMovie(movieId)
        assertNotNull(movieEntities)
        assertNotNull(movieEntities?.body?.title)
        assertNotNull(movieEntities?.body?.productionCompanies)
        assertNotNull(movieEntities?.body?.voteAverage)
        assertNotNull(movieEntities?.body?.genres)
        assertNotNull(movieEntities?.body?.posterPath)
        assertNotNull(movieEntities?.body?.backdropPath)
        assertNotNull(movieEntities?.body?.overview)
        assertNotNull(movieEntities?.body?.releaseDate)
        assertEquals(dummyDetailMovie.body?.title, movieEntities?.body?.title)
        assertEquals(
            dummyDetailMovie.body?.productionCompanies,
            movieEntities?.body?.productionCompanies
        )
        assertEquals(dummyDetailMovie.body?.voteAverage, movieEntities?.body?.voteAverage)
        assertEquals(dummyDetailMovie.body?.genres, movieEntities?.body?.genres)
        assertEquals(dummyDetailMovie.body?.posterPath, movieEntities?.body?.posterPath)
        assertEquals(dummyDetailMovie.body?.backdropPath, movieEntities?.body?.backdropPath)
        assertEquals(dummyDetailMovie.body?.overview, movieEntities?.body?.overview)
        assertEquals(dummyDetailMovie.body?.releaseDate, movieEntities?.body?.releaseDate)
    }

    @Test
    fun getDetailTvShows() {
        val tv = MutableLiveData<Resource<TvResponse>>()
        tv.postValue(dummyDetailTvShow)
        `when`(remote.getDetailTvShow(tvId)).thenReturn(tv)

        val tvShowEntities = catalogueRepository.getTvShowDetail(tvId).value
        verify(remote).getDetailTvShow(tvId)
        assertNotNull(tvShowEntities)
        assertNotNull(tvShowEntities?.body?.title)
        assertNotNull(tvShowEntities?.body?.createdBy)
        assertNotNull(tvShowEntities?.body?.voteAverage)
        assertNotNull(tvShowEntities?.body?.genres)
        assertNotNull(tvShowEntities?.body?.posterPath)
        assertNotNull(tvShowEntities?.body?.backdropPath)
        assertNotNull(tvShowEntities?.body?.overview)
        assertNotNull(tvShowEntities?.body?.firstAirDate)
        assertEquals(dummyDetailTvShow.body?.title, tvShowEntities?.body?.title)
        assertEquals(dummyDetailTvShow.body?.createdBy, tvShowEntities?.body?.createdBy)
        assertEquals(dummyDetailTvShow.body?.voteAverage, tvShowEntities?.body?.voteAverage)
        assertEquals(dummyDetailTvShow.body?.genres, tvShowEntities?.body?.genres)
        assertEquals(dummyDetailTvShow.body?.posterPath, tvShowEntities?.body?.posterPath)
        assertEquals(dummyDetailTvShow.body?.backdropPath, tvShowEntities?.body?.backdropPath)
        assertEquals(dummyDetailTvShow.body?.overview, tvShowEntities?.body?.overview)
        assertEquals(dummyDetailTvShow.body?.firstAirDate, tvShowEntities?.body?.firstAirDate)
    }

    @Test
    fun getFavMoviesTest() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavMovies()
        val favMovieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavMovies()
        assertNotNull(favMovieEntities)
        assertEquals(dummyMovie.size.toLong(), favMovieEntities.body?.size?.toLong())
    }

    @Test
    fun getFavTvShowsTest() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavTvShows()
        val favTvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        verify(local).getFavTvShows()
        assertNotNull(favTvShowEntities)
        assertEquals(dummyTv.size.toLong(), favTvShowEntities.body?.size?.toLong())
    }

    @Test
    fun insertFavMovieTest(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(local).insertFavMovie(favoriteMovieItem)
            catalogueRepository.insertFavMovie(favoriteMovieItem)
            verify(local).insertFavMovie(favoriteMovieItem)
        }
    }

    @Test
    fun insertFavTvShowTest(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(local).insertFavTvShow(favoriteTvShowItem)
            catalogueRepository.insertFavTvShow(favoriteTvShowItem)
            verify(local).insertFavTvShow(favoriteTvShowItem)
        }
    }

    @Test
    fun deleteFavMovie(){
        testScope.launch {
            val favoriteMovieItem = MovieEntity(10,"Suster Keramas","10-03-00",9.0, "Film bagus", "sfamkf3rno", "dsmkdsfn4", true)
            doNothing().`when`(local).deleteFavMovie(favoriteMovieItem)
            catalogueRepository.deleteFavMovie(favoriteMovieItem)
            verify(local).deleteFavMovie(favoriteMovieItem)
        }
    }

    @Test
    fun deleteFavTvShow(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(local).deleteFavTvShow(favoriteTvShowItem)
            catalogueRepository.deleteFavTvShow(favoriteTvShowItem)
            verify(local).deleteFavTvShow(favoriteTvShowItem)
        }
    }

    @Test
    fun isFavoritedMovie() {
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
            doNothing().`when`(local).getFavMovieById(favoriteMovieItem)
            catalogueRepository.isFavoritedMovie(favoriteMovieItem)
            verify(local).getFavMovieById(favoriteMovieItem)
        }
    }

    @Test
    fun isFavoritedTvShow(){
        testScope.launch {
            val favoriteTvShowItem = TvShowEntity(10,"Zombie Keramas","10-03-00",8.0, "Film bagus lumayan", "sfamkf3rno", "dsmkdsfn4", "10-10-00", false)
            doNothing().`when`(local).getFavTvShowById(favoriteTvShowItem)
            catalogueRepository.deleteFavTvShow(favoriteTvShowItem)
            verify(local).deleteFavTvShow(favoriteTvShowItem)
        }
    }
}