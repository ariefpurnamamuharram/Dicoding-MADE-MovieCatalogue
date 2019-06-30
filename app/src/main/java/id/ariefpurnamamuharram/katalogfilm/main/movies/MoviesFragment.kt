package id.ariefpurnamamuharram.katalogfilm.main.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.ApiClient
import id.ariefpurnamamuharram.katalogfilm.api.ApiServices
import id.ariefpurnamamuharram.katalogfilm.api.movies.Movie
import id.ariefpurnamamuharram.katalogfilm.api.movies.MoviesResponse
import id.ariefpurnamamuharram.katalogfilm.main.search.movies.SearchMoviesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var rvMovies: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_shows, container, false)
        rvMovies = rootView.findViewById(R.id.rv_shows)
        progressbar = rootView.findViewById(R.id.progress_bar)
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        // Disable swipeRefresh.
        swipeRefresh.isEnabled = false

        val apiService: ApiServices = ApiClient.getClient().create(
            ApiServices::class.java
        )

        getMovies(apiService)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                true
            }
            R.id.search -> {
                val searchFragment = SearchMoviesFragment()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, searchFragment, SearchEvent::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getMovies(apiService: ApiServices) {
        val call: Call<MoviesResponse> = apiService.getMovies()

        // Show progressbar.
        progressbar.visibility = View.VISIBLE
        rvMovies.visibility = View.INVISIBLE

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {}

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                movies = response.body()?.results ?: ArrayList()

                // Kill progressbar.
                progressbar.visibility = View.GONE
                rvMovies.visibility = View.VISIBLE

                rvMovies.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = MoviesAdapter(movies)
                }
            }
        })
    }

    companion object {
        fun newInstance(): MoviesFragment = MoviesFragment()
    }
}