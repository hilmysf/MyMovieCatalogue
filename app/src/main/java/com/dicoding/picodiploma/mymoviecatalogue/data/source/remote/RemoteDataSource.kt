package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*
import com.dicoding.picodiploma.mymoviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: MovieApiService) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val TAG = "RemoteRepository"
        private const val TIME_IN_MILLIS: Long = 1500
    }

    fun getPopularMovies(callback: LoadPopoularMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiService.getMoviePopular().enqueue(object : retrofit2.Callback<PopularMovieResponse> {
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>
                ) {
                    response.body()?.results.let { callback.onPopularMoviesReceived(it!!) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun getPopularTv(callback: LoadPopoularTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiService.getTvPopular().enqueue(object : retrofit2.Callback<PopularTvResponse> {

                override fun onResponse(
                    call: Call<PopularTvResponse>,
                    response: Response<PopularTvResponse>
                ) {
                    response.body()?.results.let { callback.onPopoularTvReceived(it!!) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<PopularTvResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun getDetailMovie(movieId: Int, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiService.getMovieDetail(movieId).enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    callback.onDetailMovieReceived(response.body()!!)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun getDetailTv(tvId: Int, callback: LoadDetaiTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiService.getTvShowDetail(tvId).enqueue(object : retrofit2.Callback<TvResponse> {
                override fun onResponse(
                    call: Call<TvResponse>,
                    response: Response<TvResponse>
                ) {
                    callback.onDetailTvReceived(response.body()!!)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

            })
        }, TIME_IN_MILLIS)
    }

    interface LoadPopoularMoviesCallback {
        fun onPopularMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadPopoularTvCallback {
        fun onPopoularTvReceived(tvResponse: List<TvResponse>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(contentResponse: MovieResponse)
    }

    interface LoadDetaiTvCallback {
        fun onDetailTvReceived(contentResponse: TvResponse)
    }
}