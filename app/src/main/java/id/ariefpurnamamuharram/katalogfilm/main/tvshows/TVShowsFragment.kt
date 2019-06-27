package id.ariefpurnamamuharram.katalogfilm.main.tvshows

import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.tvshow.TVShow

class TVShowsFragment : Fragment() {
    private lateinit var tvShowTitle: Array<String>
    private lateinit var tvShowReleaseDate: Array<String>
    private lateinit var tvShowUserScore: Array<String>
    private lateinit var tvShowOverview: Array<String>
    private lateinit var tvShowPoster: TypedArray

    private lateinit var rootView: View
    private lateinit var rvTVShows: RecyclerView

    private var tvShows: ArrayList<TVShow> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_shows, container, false)

        rvTVShows = rootView.findViewById(R.id.rv_shows)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Prepare TVShows data.
        prepare()
        addItem()

        // Setup movie RecyclerView.
        rvTVShows.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TVShowsAdapter(tvShows)
        }
    }

    private fun addItem() {
        var i = 0
        while (i < tvShowTitle.size) {
            tvShows.add(
                i, TVShow(
                    tvShowPoster.getResourceId(i, -1),
                    tvShowTitle[i],
                    tvShowReleaseDate[i],
                    tvShowUserScore[i],
                    tvShowOverview[i]
                )
            )
            i++
        }
    }

    private fun prepare() {
        tvShowPoster = resources.obtainTypedArray(R.array.tv_poster)
        tvShowTitle = resources.getStringArray(R.array.tv_title)
        tvShowReleaseDate = resources.getStringArray(R.array.tv_release_date)
        tvShowUserScore = resources.getStringArray(R.array.tv_user_score)
        tvShowOverview = resources.getStringArray(R.array.tv_overview)
    }


    companion object {
        fun newInstance(): TVShowsFragment = TVShowsFragment()
    }
}