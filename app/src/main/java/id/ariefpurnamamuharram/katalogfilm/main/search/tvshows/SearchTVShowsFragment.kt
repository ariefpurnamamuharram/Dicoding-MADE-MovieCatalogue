package id.ariefpurnamamuharram.katalogfilm.main.search.tvshows

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.ApiClient
import id.ariefpurnamamuharram.katalogfilm.api.ApiServices
import id.ariefpurnamamuharram.katalogfilm.api.searchtvshows.SearchTVShows
import id.ariefpurnamamuharram.katalogfilm.api.searchtvshows.SearchTVShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTVShowsFragment : Fragment() {
    private lateinit var rvSearchResults: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var apiServices: ApiServices
    private var searchResults: ArrayList<SearchTVShows> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        rvSearchResults = rootView.findViewById(R.id.rv_search_results)
        progressbar = rootView.findViewById(R.id.progress_bar)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        // Kill progressbar.
        progressbar.visibility = View.GONE
        rvSearchResults.visibility = View.VISIBLE

        apiServices = ApiClient.getClient().create(
            ApiServices::class.java
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = resources.getString(R.string.search)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchResults(apiServices, query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener { true }
    }

    private fun getSearchResults(apiServices: ApiServices, query: String) {
        val call: Call<SearchTVShowsResponse> = apiServices.getSearchTVShows(query)

        // Show progressbar.
        progressbar.visibility = View.VISIBLE
        rvSearchResults.visibility = View.INVISIBLE

        call.enqueue(object : Callback<SearchTVShowsResponse> {
            override fun onFailure(call: Call<SearchTVShowsResponse>, t: Throwable) {}

            override fun onResponse(call: Call<SearchTVShowsResponse>, response: Response<SearchTVShowsResponse>) {
                searchResults.clear()
                searchResults = response.body()?.results ?: ArrayList()

                rvSearchResults.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = SearchTVShowsAdapter(searchResults)
                }

                // Kill progressbar.
                progressbar.visibility = View.GONE
                rvSearchResults.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        fun newInstance(): SearchTVShowsFragment = SearchTVShowsFragment()
    }
}