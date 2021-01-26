package com.dicoding.picodiploma.mymoviecatalogue.data

data class MovieEntity(
    val movieId: String,
    val title: String,
    val creator: String,
    val genre: String,
    val releaseDate: String,
    val score: String,
    val overview: String,
    val poster: Int
)