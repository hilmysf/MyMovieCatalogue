package com.dicoding.picodiploma.mymoviecatalogue.ui

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.TvResponse
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ItemsMoviesBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.detail.DetailActivity

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private var listTv: List<TvResponse> = emptyList()
    fun setTv(tv: List<TvResponse>?) {
        if (tv == null) return
        this.listTv = tv
        notifyDataSetChanged()
    }

    class TvViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvResponse) {
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
                    intent.putExtra(DetailActivity.EXTRA_TITLE, tv.title)
                    intent.putExtra(DetailActivity.EXTRA_ID, tv.tvId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "tv")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvViewHolder {
        val itemsTvBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvBinding)
    }

    override fun getItemCount(): Int = listTv.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }
}