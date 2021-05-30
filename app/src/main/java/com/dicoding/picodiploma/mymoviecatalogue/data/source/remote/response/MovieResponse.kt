package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieResponse(

//	@field:SerializedName("original_language")
//	val originalLanguage: String? = null,

//	@field:SerializedName("imdb_id")
//	val imdbId: String? = null,

//	@field:SerializedName("video")
//	val video: Boolean? = null,

//	val movieId: String,
//	val title: String,
//	val creator: String,
//	val genre: String,
//	val releaseDate: String,
//	val score: String,
//	val overview: String,
//	val poster: Int

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("revenue")
	val revenue: Int? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("id")
	val movieId: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("budget")
	val budget: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

//	@field:SerializedName("spoken_languages")
//	val spokenLanguages: List<SpokenLanguagesItem?>? = null,
//
	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem?>? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("belongs_to_collection")
	val belongsToCollection: Any? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

//data class ProductionCountriesItem(
//
//	@field:SerializedName("iso_3166_1")
//	val iso31661: String? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null
//)

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
