package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ItemsMoviesBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.detail.DetailActivity
import com.dicoding.picodiploma.mymoviecatalogue.ui.detail.DetailActivity.Companion.TYPE_MOVIE

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies: List<MovieEntity> = emptyList()
    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies = movies
        notifyDataSetChanged()
    }

    class MoviesViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity?) {
            with(binding) {
                tvTitle.text = movie?.title
                tvReleaseDate.text = movie?.releaseDate
                tvOverview.text = movie?.overview
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    tvOverview.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemsMovieBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMovieBinding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }
}