package id.ariefpurnamamuharram.katalogfilm.main.favorites.tvshows

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
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteTVShow
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTVShowsFragment : Fragment() {
    private lateinit var tvShowsAdapter: FavoriteTVShowsAdapter
    private lateinit var rvFavorites: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var favorites: MutableList<FavoriteTVShow> = mutableListOf()

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

        tvShowsAdapter = FavoriteTVShowsAdapter(favorites)

        rvFavorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tvShowsAdapter
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
            val result = select(FavoriteTVShow.TABLE_FAVORITE_TV_SHOW)
            val favorite = result.parseList(classParser<FavoriteTVShow>())
            favorites.addAll(favorite)
            tvShowsAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteTVShowsFragment = FavoriteTVShowsFragment()
    }
}