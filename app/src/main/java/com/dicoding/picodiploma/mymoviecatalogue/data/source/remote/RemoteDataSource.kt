package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*
import com.dicoding.picodiploma.mymoviecatalogue.utils.EspressoIdlingResource
import com.dicoding.picodiploma.mymoviecatalogue.vo.Resource
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: MovieApiService) {

    val popularMovies = MutableLiveData<Resource<List<MovieEntity>>>()
    val popularTvShows = MutableLiveData<Resource<List<TvShowEntity>>>()
    val detailMovie = MutableLiveData<Resource<MovieResponse>>()
    val detailTvShows = MutableLiveData<Resource<TvResponse>>()

    companion object {
        private const val TAG = "RemoteRepository"
    }

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        EspressoIdlingResource.increment()
        popularMovies.postValue(Resource.loading(null))
              apiService.getMoviePopular().enqueue(object : retrofit2.Callback<PopularMovieResponse> {
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>
                ) {
                    popularMovies.postValue(handleMoviesResponse(response))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        return popularMovies
    }

    private fun handleMoviesResponse(response: Response<PopularMovieResponse>): Resource<List<MovieEntity>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse.results)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getPopularTv(): LiveData<Resource<List<TvShowEntity>>> {
        EspressoIdlingResource.increment()
        popularTvShows.postValue(Resource.loading(null))
            apiService.getTvPopular().enqueue(object : retrofit2.Callback<PopularTvResponse> {
                override fun onResponse(
                    call: Call<PopularTvResponse>,
                    response: Response<PopularTvResponse>
                ) {
                    popularTvShows.postValue(handleTvShowsResponse(response))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<PopularTvResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        return popularTvShows
    }

    private fun handleTvShowsResponse(response: Response<PopularTvResponse>): Resource<List<TvShowEntity>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse.results)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getDetailMovie(movieId: Int?): LiveData<Resource<MovieResponse>> {
        EspressoIdlingResource.increment()
        detailMovie.postValue(Resource.loading(null))
            apiService.getMovieDetail(movieId).enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    detailMovie.postValue(handleMovieDetailResponse(response))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        return detailMovie
    }

    private fun handleMovieDetailResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getDetailTvShow(tvId: Int?): LiveData<Resource<TvResponse>> {
        EspressoIdlingResource.increment()
        detailTvShows.postValue(Resource.loading(null))
            apiService.getTvShowDetail(tvId).enqueue(object : retrofit2.Callback<TvResponse> {
                override fun onResponse(
                    call: Call<TvResponse>,
                    response: Response<TvResponse>
                ) {
                    detailTvShows.postValue(handleTvShowDetailResponse(response))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        return detailTvShows
    }

    private fun handleTvShowDetailResponse(response: Response<TvResponse>): Resource<TvResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse)
            }
        }
        return Resource.error(response.message(), null)
    }
}