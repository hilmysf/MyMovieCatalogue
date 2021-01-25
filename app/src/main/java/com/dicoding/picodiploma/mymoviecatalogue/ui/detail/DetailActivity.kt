package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        var EXTRA_TITLE = "extra_title"
        var EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val movieId = intent.getStringExtra(EXTRA_ID)
        supportActionBar?.title = title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detailBinding.tvOverviewContent.justificationMode =
                LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        if (title != null) {
            if (movieId != null) {
                if (movieId?.take(1) == "m") {
                    showMovieDetail(viewModel, movieId)
                }
                if (movieId?.take(1) == "t") {
                    showTvDetail(viewModel, movieId)
                }
            }
        }
    }

    private fun showMovieDetail(viewModel: DetailViewModel, movieId: String) {
        viewModel.setMovieList(movieId)
        val moviesList = viewModel.getMoviesList()
        Glide.with(applicationContext)
            .load(moviesList.poster)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(detailBinding.imageView)
        detailBinding.tvTitle.text = moviesList.title
        detailBinding.tvReleaseDate.text = moviesList.releaseDate
        detailBinding.tvScore.text = moviesList.score
        detailBinding.tvGenre.text = moviesList.genre
        detailBinding.tvCreatorName.text = moviesList.creator
        detailBinding.tvOverviewContent.text = moviesList.overview
    }

    private fun showTvDetail(viewModel: DetailViewModel, movieId: String) {
        viewModel.setMovieList(movieId)
        val moviesList = viewModel.getTvList()
        Glide.with(applicationContext)
            .load(moviesList.poster)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(detailBinding.imageView)
        detailBinding.tvTitle.text = moviesList.title
        detailBinding.tvReleaseDate.text = moviesList.releaseDate
        detailBinding.tvScore.text = moviesList.score
        detailBinding.tvGenre.text = moviesList.genre
        detailBinding.tvCreatorName.text = moviesList.creator
        detailBinding.tvOverviewContent.text = moviesList.overview
    }
}