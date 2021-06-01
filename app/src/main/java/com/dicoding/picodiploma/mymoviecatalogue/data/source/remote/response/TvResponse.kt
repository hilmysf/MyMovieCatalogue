package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(

	@field:SerializedName("id")
	val tvId: Int? = null,
	@field:SerializedName("name")
	val title: String? = null,
	@field:SerializedName("created_by")
	val createdBy: List<CreatedByItem?>? = null,
	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,
	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,
	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,
	@field:SerializedName("overview")
	val overview: String? = null,
	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,
	@field:SerializedName("last_episode_to_air")
	val lastEpisodeToAir: LastEpisodeToAir? = null,
	@field:SerializedName("poster_path")
	val posterPath: String? = null,
	@field:SerializedName("last_air_date")
	val lastAirDate: String? = null
)

data class LastEpisodeToAir(

	@field:SerializedName("production_code")
	val productionCode: String? = null,

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("episode_number")
	val episodeNumber: Int? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("still_path")
	val stillPath: String? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)

data class CreatedByItem(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)