package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        var EXTRA_TITLE = "extra_title"
        var EXTRA_ID = "extra_id"
        var EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val movieId = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)
        supportActionBar?.title = title
        supportActionBar?.elevation = 1f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            detailBinding.tvOverviewContent.justificationMode =
                LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
        if (title != null) {
            showLoading(true)
            if (type.equals("movie")) {
                showMovieDetail(movieId)
            }
            if (type.equals("tv")) {
                showTvDetail(movieId)
            }
        }
        shareButtonAction()
    }

    private fun shareButtonAction() {
        detailBinding.btnShare.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Let's watch $title with your loved ones")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Don't forget to re-share this movie")
            shareIntent.type = "text/plain"
            startActivity(shareIntent)
        }
    }

    private fun showMovieDetail(movieId: Int) {
        viewModel.getDetailMovie(movieId).observe(this, Observer {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(detailBinding.imageView)
            detailBinding.tvTitle.text = it.title
            detailBinding.tvReleaseDate.text = it.releaseDate
            detailBinding.tvScore.text = it.voteAverage.toString()
            detailBinding.tvGenre.text = it.genres?.get(0)?.name
            detailBinding.tvCreatorName.text = it.productionCompanies?.get(0)?.name
            detailBinding.tvOverviewContent.text = it.overview
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(detailBinding.imgBackground)
        })
        showLoading(false)
    }

    private fun showTvDetail(tvId: Int) {
        viewModel.getDetailTv(tvId).observe(this, Observer {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(detailBinding.imageView)
            detailBinding.tvTitle.text = it.title
            detailBinding.tvReleaseDate.text = it.firstAirDate
            detailBinding.tvScore.text = it.voteAverage.toString()
            detailBinding.tvGenre.text = it.genres?.get(0)?.name
            detailBinding.tvCreatorName.text = it.createdBy?.get(0)?.name
            detailBinding.tvOverviewContent.text = it.lastEpisodeToAir?.overview
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(detailBinding.imgBackground)
        })
        showLoading(false)
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}