package id.ariefpurnamamuharram.katalogfilm.main.tvshows

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.ApiClient
import id.ariefpurnamamuharram.katalogfilm.api.ApiServices
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TvShow
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TvShowsResponse
import kotlinx.android.synthetic.main.fragment_shows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowsFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var rvTVShows: RecyclerView
    private var tvShows: ArrayList<TvShow> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_shows, container, false)
        rvTVShows = rootView.findViewById(R.id.rv_shows)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiService: ApiServices = ApiClient.getClient().create(
            ApiServices::class.java
        )

        // Load TV shows.
        getTvShows(apiService)
    }

    private fun getTvShows(apiService: ApiServices) {
        val call: Call<TvShowsResponse> = apiService.getTvShows()

        // Show progressbar.
        progress_bar.visibility = View.VISIBLE
        rvTVShows.visibility = View.INVISIBLE

        call.enqueue(object : Callback<TvShowsResponse> {
            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {}

            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                tvShows = response.body()!!.results

                // Kill progressbar.
                progress_bar.visibility = View.GONE
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