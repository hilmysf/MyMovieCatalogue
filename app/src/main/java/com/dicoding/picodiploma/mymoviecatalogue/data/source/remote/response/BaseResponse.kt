package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

	@SerializedName("page")
	val page: Int,

	@SerializedName("results")
	val results: List<MovieResponse>

)

data class PopularTvResponse(

	@SerializedName("page")
	val page: Int,

	@SerializedName("results")
	val results: List<TvResponse>

)
