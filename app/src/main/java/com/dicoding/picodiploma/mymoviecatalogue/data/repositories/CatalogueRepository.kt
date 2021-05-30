package com.dicoding.picodiploma.mymoviecatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {
//    companion object {
//        @Volatile
//        private var INSTANCE: CatalogueRepository? = null
//
//
//        fun getInstance(remoteData: RemoteDataSource): CatalogueDataSource? {
//            if (INSTANCE == null){
//                synchronized(CatalogueRepository::class.java){
//                    if (INSTANCE == null)
//                        INSTANCE = CatalogueRepository(remoteData)
//                }
//            }
//        return INSTANCE
//    }
//}

    override fun getPopularMovies(): LiveData<List<MovieResponse>> {
        val movieLists = MutableLiveData<List<MovieResponse>>()
        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopoularMoviesCallback{
            override fun onPopularMoviesReceived(movieResponse: List<MovieResponse>) {
                movieLists.postValue(movieResponse)
            }

        })
        return movieLists
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieResponse> {
        val movieDetail = MutableLiveData<MovieResponse>()
        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback{
            override fun onDetailMovieReceived(contentResponse: MovieResponse) {
                movieDetail.postValue(contentResponse)
            }
        })
        return  movieDetail
    }

    override fun getPopularTvShow(): LiveData<List<TvResponse>> {
        val tvLists = MutableLiveData<List<TvResponse>>()
        remoteDataSource.getPopularTv(object : RemoteDataSource.LoadPopoularTvCallback{
            override fun onPopoularTvReceived(tvResponse: List<TvResponse>) {
                tvLists.postValue(tvResponse)
            }
        })
        return tvLists
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvResponse> {
        val tvDetail = MutableLiveData<TvResponse>()
        remoteDataSource.getDetailTv(tvShowId, object : RemoteDataSource.LoadDetaiTvCallback{
            override fun onDetailTvReceived(contentResponse: TvResponse) {
                tvDetail.postValue(contentResponse)
            }
        })
        return  tvDetail
    }
}