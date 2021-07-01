package com.dicoding.picodiploma.mymoviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.mymoviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTv()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        val random = (0..9).random()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_creator_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
    }

    @Test
    fun insertFavoriteMovie() {
        val random = (0..9).random()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShows() {
        val random = (0..9).random()
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_creator_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
    }

    @Test
    fun insertFavoriteTvShow() {
        val random = (0..9).random()
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
    }

    @Test
    fun deleteFavoriteMoviesItem() {
        val random = (0..9).random()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
    }
    @Test
    fun deleteFavoriteTvShowsItem() {
        val random = (0..9).random()
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                random,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withText("MY TV SHOWS")).perform(click())
        onView(withId(R.id.rv_favorite_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
    }
}