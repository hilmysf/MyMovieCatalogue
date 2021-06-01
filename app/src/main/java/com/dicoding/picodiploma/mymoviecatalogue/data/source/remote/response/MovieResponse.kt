package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("id")
	val movieId: Int? = null,
	@field:SerializedName("title")
	val title: String? = null,
	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem?>? = null,
	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,
	@field:SerializedName("release_date")
	val releaseDate: String? = null,
	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,
	@field:SerializedName("overview")
	val overview: String? = null,
	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,
	@field:SerializedName("poster_path")
	val posterPath: String? = null
)

data class ProductionCompaniesItem(
	@field:SerializedName("id")
	val id: String? = null,
	@field:SerializedName("logo_path")
	val logoPath: String? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("origin_country")
	val originCountry: String? = null
)

data class GenresItem(
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("id")
	val id: Int? = null
)
