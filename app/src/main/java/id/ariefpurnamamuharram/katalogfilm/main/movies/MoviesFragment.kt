package id.ariefpurnamamuharram.katalogfilm.main.movies

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
import id.ariefpurnamamuharram.katalogfilm.api.movies.Movie
import id.ariefpurnamamuharram.katalogfilm.api.movies.MoviesResponse
import kotlinx.android.synthetic.main.fragment_shows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var rvMovies: RecyclerView
    private var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_shows, container, false)
        rvMovies = rootView.findViewById(R.id.rv_shows)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiService: ApiServices = ApiClient.getClient().create(
            ApiServices::class.java
        )

        // Load movies.
        getMovies(apiService)
    }

    private fun getMovies(apiService: ApiServices) {
        val call: Call<MoviesResponse> = apiService.getMovies()

        // Show progressbar.
        progress_bar.visibility = View.VISIBLE
        rvMovies.visibility = View.INVISIBLE

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {}

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                movies = response.body()!!.results

                // Kill progressbar.
                progress_bar.visibility = View.GONE
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