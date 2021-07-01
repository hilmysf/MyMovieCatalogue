package com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @field:SerializedName("id")
    val tvId: Int? = null,
    @field:SerializedName("name")
    val title: String? = null,
    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    @field:SerializedName("overview")
    val overview: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("last_air_date")
    val lastAirDate: String? = null,
    var bookmarked: Boolean? = false
): Parcelable