package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote

import com.dicoding.picodiploma.mymoviecatalogue.BuildConfig.API_KEY
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") id: Int?,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<TvResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<PopularMovieResponse>

    @GET("tv/popular")
    fun getTvPopular(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<PopularTvResponse>
}