package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.favorite.FavViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvShowFragment : androidx.fragment.app.Fragment() {
    private lateinit var fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding
    private val viewModel: FavViewModel by viewModels()
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteTvShowBinding =
            FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteTvShowBinding.rvFavoriteTvshows)
        favoriteTvShowAdapter = FavoriteTvShowAdapter()
        getTvShowsData()
    }

    private fun getTvShowsData() {
        viewModel.getFavTvShows().observe(viewLifecycleOwner, {
            favoriteTvShowAdapter.submitList(it)
        })
        with(fragmentFavoriteTvShowBinding.rvFavoriteTvshows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = favoriteTvShowAdapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteTvShowAdapter.getSwipeData(swipedPosition)
                movieEntity?.let { viewModel.deleteFavTvShow(it) }

                val snackbar =
                    Snackbar.make(view as View, "Removed From Favorite", Snackbar.LENGTH_LONG)
                snackbar.setAction("Undo") {
                    movieEntity?.let { viewModel.insertFavTvShow(it) }
                }
                snackbar.show()
            }
        }
    })
}