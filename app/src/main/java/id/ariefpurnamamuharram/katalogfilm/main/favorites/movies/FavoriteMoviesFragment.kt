package id.ariefpurnamamuharram.katalogfilm.main.favorites.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.db.db
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteMovie
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMoviesFragment : Fragment() {
    private lateinit var moviesAdapter: FavoriteMoviesAdapter
    private lateinit var rvFavorites: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var favorites: MutableList<FavoriteMovie> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_shows, container, false)

        rvFavorites = rootView.findViewById(R.id.rv_shows)
        progressbar = rootView.findViewById(R.id.progress_bar)
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh)

        // Setup swipeRefresh.
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)

        progressbar.visibility = View.GONE

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesAdapter = FavoriteMoviesAdapter(favorites)

        rvFavorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = moviesAdapter
        }

        showFavorites()

        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorites()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun showFavorites() {
        context?.db?.use {
            val result = select(FavoriteMovie.TABLE_FAVORITE_MOVIE)
            val favorite = result.parseList(classParser<FavoriteMovie>())
            favorites.addAll(favorite)
            moviesAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteMoviesFragment = FavoriteMoviesFragment()
    }
}