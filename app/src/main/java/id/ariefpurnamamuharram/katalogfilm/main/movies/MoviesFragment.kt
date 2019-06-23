package id.ariefpurnamamuharram.katalogfilm.main.movies

import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.movie.Movie

class MoviesFragment : Fragment() {
    private lateinit var movieTitle: Array<String>
    private lateinit var movieReleaseDate: Array<String>
    private lateinit var movieUserScore: Array<String>
    private lateinit var movieOverview: Array<String>
    private lateinit var moviePoster: TypedArray

    private lateinit var rootView: View
    private lateinit var rvMovies: RecyclerView

    private var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false)

        rvMovies = rootView.findViewById(R.id.rv_movies)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Prepare movies data.
        prepare()
        addItem()

        // Setup movie RecyclerView.
        rvMovies.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MoviesAdapter(movies)
        }
    }

    private fun addItem() {
        var i = 0
        while (i < movieTitle.size) {
            movies.add(
                i, Movie(
                    moviePoster.getResourceId(i, -1),
                    movieTitle[i],
                    movieReleaseDate[i],
                    movieUserScore[i],
                    movieOverview[i]
                )
            )
            i++
        }
    }

    private fun prepare() {
        moviePoster = resources.obtainTypedArray(R.array.movie_poster)
        movieTitle = resources.getStringArray(R.array.movie_title)
        movieReleaseDate = resources.getStringArray(R.array.movie_release_date)
        movieUserScore = resources.getStringArray(R.array.movie_user_score)
        movieOverview = resources.getStringArray(R.array.movie_overview)
    }


    companion object {
        fun newInstance(): MoviesFragment = MoviesFragment()
    }
}