package com.dicoding.picodiploma.mymoviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentMovieBinding
import com.dicoding.picodiploma.mymoviecatalogue.ui.MoviesAdapter
import com.dicoding.picodiploma.mymoviecatalogue.ui.ViewModelFactory
import com.dicoding.picodiploma.mymoviecatalogue.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private var movie = listOf<MovieResponse>()
    private val viewModel: MovieViewModel by viewModels()
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
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(
//                this,
//                ViewModelProvider.NewInstanceFactory()
//            )[MovieViewModel::class.java]

            val movieAdapter = MoviesAdapter()
            viewModel.getPopularMovie.observe(viewLifecycleOwner, Observer {
                movie = it
                movieAdapter.setMovies(movie)
            })
            with(fragmentMovieBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}