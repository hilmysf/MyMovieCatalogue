package com.dicoding.picodiploma.mymoviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentTvShowBinding
import com.dicoding.picodiploma.mymoviecatalogue.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var tvAdapter: TvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAdapter = TvAdapter()
        getTvShowsData()
    }

    private fun getTvShowsData() {
        viewModel.getPopularTv().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    tvAdapter.setTv(it.body)
                    tvAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

        })
        with(fragmentTvShowBinding.rvTvshows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = tvAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvShowBinding.progressBar.visibility = View.GONE
        }
    }
}