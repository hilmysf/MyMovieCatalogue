package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieEntity>

)