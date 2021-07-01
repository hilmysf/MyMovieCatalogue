package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.data.source.local.entities.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ItemsMoviesBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.detail.DetailActivity
import com.dicoding.picodiploma.mymoviecatalogue.ui.detail.DetailActivity.Companion.TYPE_TV

class FavoriteTvShowAdapter :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.TvShowsViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TvShowsViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShowEntity) {
            with(binding) {
                tvTitle.text = tv.title
                tvReleaseDate.text = tv.firstAirDate
                tvOverview.text = tv.overview
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    tvOverview.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV, tv)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowsViewHolder {
        val itemsTvBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    fun getSwipeData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)
}