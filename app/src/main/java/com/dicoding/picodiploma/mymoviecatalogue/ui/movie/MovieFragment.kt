package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentMovieBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.MoviesAdapter

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = viewModel.getMoviesList()

            val movieAdapter = MoviesAdapter()
            movieAdapter.setMovies(movies)

            with(fragmentMovieBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}