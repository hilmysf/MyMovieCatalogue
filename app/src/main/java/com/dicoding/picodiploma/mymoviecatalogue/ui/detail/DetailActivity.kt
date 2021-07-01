package com.dicoding.picodiploma.mymoviecatalogue.ui.detail

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.mymoviecatalogue.vo.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var movie: MovieEntity? = null
    private var tv: TvShowEntity? = null
    private var type: String? = null

    companion object {
        var EXTRA_MOVIE = "movie"
        var EXTRA_TV = "tv"
        var EXTRA_TITLE = "extra_title"
        var EXTRA_ID = "extra_id"
        var EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)
        tv = intent.getParcelableExtra(EXTRA_TV)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val movieId = intent.getIntExtra(EXTRA_ID, 0)
        type = intent.getStringExtra(EXTRA_TYPE)
        supportActionBar?.title = if (type.equals("movie")) movie?.title else tv?.title
        supportActionBar?.elevation = 1f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            detailBinding.tvOverviewContent.justificationMode =
                LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        showLoading(true)
        if (type.equals("movie")) {
            showMovieDetail(movie)
            fabOnClick(viewModel.isFavoritedMovie(movie))
            setFavoriteState(viewModel.isFavoritedMovie(movie))
        }
        else if(type.equals("tv")){
            showTvDetail(tv)
            fabOnClick(viewModel.isFavoritedTvShow(tv))
            setFavoriteState(viewModel.isFavoritedTvShow(tv))
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

    private fun showMovieDetail(movie: MovieEntity?) {
        viewModel.getDetailMovie(movie?.movieId).observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.posterPath}")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(detailBinding.imageView)
                    detailBinding.tvTitle.text = it.body?.title
                    detailBinding.tvReleaseDate.text = it.body?.releaseDate
                    detailBinding.tvScore.text = it.body?.voteAverage.toString()
                    detailBinding.tvGenre.text = it.body?.genres?.get(0)?.name
                    detailBinding.tvCreatorName.text = it.body?.productionCompanies?.get(0)?.name
                    detailBinding.tvOverviewContent.text = it.body?.overview
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.backdropPath}")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(detailBinding.imgBackground)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(this, "Terdapat Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
        )
    }

    private fun showTvDetail(tvShow: TvShowEntity?) {
        viewModel.getDetailTv(tvShow?.tvId).observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.posterPath}")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(detailBinding.imageView)
                    detailBinding.tvTitle.text = it.body?.title
                    detailBinding.tvReleaseDate.text = it.body?.firstAirDate
                    detailBinding.tvScore.text = it.body?.voteAverage.toString()
                    detailBinding.tvGenre.text = it.body?.genres?.get(0)?.name
                    detailBinding.tvCreatorName.text = it.body?.createdBy?.get(0)?.name
                    detailBinding.tvOverviewContent.text = it.body?.lastEpisodeToAir?.overview
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.backdropPath}")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(detailBinding.imgBackground)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailBinding.progressBar.visibility = View.GONE
        }
    }

    private fun setFavoriteState(state: Boolean?) {
        if (state == true) {
            detailBinding.fabAdd.setImageDrawable(getDrawable(R.drawable.ic_favorited))
        } else {
            detailBinding.fabAdd.setImageDrawable(getDrawable(R.drawable.ic_favorite_border))
        }
    }

    private fun fabOnClick(state: Boolean?) {
        detailBinding.fabAdd.setOnClickListener {
            if (type.equals("movie")) {
                when (viewModel.isFavoritedMovie(movie)) {
                    true -> {
                        movie?.let { viewModel.deleteFavMovie(it) }
                        Snackbar.make(it, R.string.delete_favorite, Snackbar.LENGTH_SHORT).show()
                        setFavoriteState(false)
                    }
                    false -> {
                        movie?.let { viewModel.insertFavMovie(it) }
                        Snackbar.make(it, R.string.add_favorite, Snackbar.LENGTH_SHORT).show()
                        setFavoriteState(true)
                    }
                }
            } else {
                when (viewModel.isFavoritedTvShow(tv)) {
                    true -> {
                        tv?.let { viewModel.deleteFavTvShow(it) }
                        Snackbar.make(it, R.string.delete_favorite, Snackbar.LENGTH_SHORT).show()
                        setFavoriteState(false)
                    }
                    false -> {
                        tv?.let { viewModel.insertFavTvShow(it) }
                        Snackbar.make(it, R.string.add_favorite, Snackbar.LENGTH_SHORT).show()
                        setFavoriteState(true)
                    }
                }
            }
        }
    }
}