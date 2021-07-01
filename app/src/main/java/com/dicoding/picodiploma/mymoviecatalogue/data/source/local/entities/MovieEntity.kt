package com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @field:SerializedName("id")
    val movieId: Int? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("release_date")
    val releaseDate: String? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    @field:SerializedName("overview")
    val overview: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    var bookmarked: Boolean? = false
): Parcelable
