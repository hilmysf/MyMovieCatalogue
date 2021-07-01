package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.favorite.FavViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : androidx.fragment.app.Fragment() {
    private lateinit var fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding
    private val viewModel: FavViewModel by viewModels()
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteMovieBinding.rvFavoriteMovies)
        favoriteMovieAdapter = FavoriteMovieAdapter()
        getMoviesData()
    }

    private fun getMoviesData(){
        viewModel.getFavMovies().observe(viewLifecycleOwner, {
            favoriteMovieAdapter.submitList(it)
        })
        with(fragmentFavoriteMovieBinding.rvFavoriteMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = favoriteMovieAdapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteMovieAdapter.getSwipeData(swipedPosition)
                movieEntity?.let { viewModel.deleteFavMovie(it) }

                val snackbar = Snackbar.make(view as View, "Removed From Favorite", Snackbar.LENGTH_LONG)
                snackbar.setAction("Undo") {
                    movieEntity?.let { viewModel.insertFavMovie(it) }
                }
                snackbar.show()
            }
        }
    })
}