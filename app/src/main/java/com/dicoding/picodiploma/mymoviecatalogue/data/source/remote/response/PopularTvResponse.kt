package com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response

import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.google.gson.annotations.SerializedName

data class PopularTvResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<TvShowEntity>

)