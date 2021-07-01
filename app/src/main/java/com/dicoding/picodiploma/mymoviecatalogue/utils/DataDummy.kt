package com.dicoding.picodiploma.mymoviecatalogue.utils

import android.util.Log
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.PopularMovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.PopularTvResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

object DataDummy {

    private const val TAG = "DataDummy"
    private var gson = Gson()

    fun generateDummyMovies() =
        gson.fromJson(parsingFileToString("movies.json"), PopularMovieResponse::class.java).results

    fun generateDummyTv() =
        gson.fromJson(parsingFileToString("tv.json"), PopularTvResponse::class.java).results

    fun generateDummyDetailMovie(): MovieResponse =
        gson.fromJson(parsingFileToString("detailMovie.json"), MovieResponse::class.java)

    fun generateDummyDetailTv(): TvResponse =
        gson.fromJson(parsingFileToString("detailTv.json"), TvResponse::class.java)

    private fun parsingFileToString(fileName: String): String? {
        var json: String? = null
        try {
            val input: InputStream = this.javaClass.classLoader.getResourceAsStream(fileName)
            val size = input.available()
            val buffer = ByteArray(size)

            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage.orEmpty())
        }
        return json
    }
}