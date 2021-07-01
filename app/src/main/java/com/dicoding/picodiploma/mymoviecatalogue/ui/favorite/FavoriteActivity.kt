package com.dicoding.picodiploma.mymoviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
        supportActionBar?.title = getString(R.string.favorite_menu)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = activityFavoriteBinding.viewPagerFavorite
        viewPager.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabsFavorite.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f

    }
}