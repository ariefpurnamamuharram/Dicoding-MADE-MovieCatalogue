package id.ariefpurnamamuharram.katalogfilm.main.tvshows

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
import id.ariefpurnamamuharram.katalogfilm.api.ApiClient
import id.ariefpurnamamuharram.katalogfilm.api.ApiServices
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShow
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowsFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var rvTVShows: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var tvShows: ArrayList<TVShow> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_shows, container, false)
        rvTVShows = rootView.findViewById(R.id.rv_shows)
        progressbar = rootView.findViewById(R.id.progress_bar)
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Disable swipeRefresh.
        swipeRefresh.isEnabled = false

        val apiService: ApiServices = ApiClient.getClient().create(
            ApiServices::class.java
        )

        getTvShows(apiService)
    }

    private fun getTvShows(apiService: ApiServices) {
        val call: Call<TVShowsResponse> = apiService.getTvShows()

        // Show progressbar.
        progressbar.visibility = View.VISIBLE
        rvTVShows.visibility = View.INVISIBLE

        call.enqueue(object : Callback<TVShowsResponse> {
            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {}

            override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                tvShows = response.body()!!.results

                // Kill progressbar.
                progressbar.visibility = View.GONE
                rvTVShows.visibility = View.VISIBLE

                rvTVShows.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = TVShowsAdapter(tvShows)
                }
            }
        })
    }

    companion object {
        fun newInstance(): TVShowsFragment = TVShowsFragment()
    }
}