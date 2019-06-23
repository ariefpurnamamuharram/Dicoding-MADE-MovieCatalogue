package id.ariefpurnamamuharram.katalogfilm.main

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.movie.Movie
import id.ariefpurnamamuharram.katalogfilm.details.MovieDetails

class MainActivity : AppCompatActivity() {
    private lateinit var movieList: ListView
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieTitle: Array<String>
    private lateinit var movieReleaseDate: Array<String>
    private lateinit var movieUserScore: Array<String>
    private lateinit var movieOverview: Array<String>
    private lateinit var moviePoster: TypedArray

    private var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare movies data.
        prepare()
        addItem()

        // Setup movie list.
        movieListAdapter = MovieListAdapter(this, movies)
        movieList = findViewById(R.id.movie_list)
        movieList.adapter = movieListAdapter

        // Setup movie click listener.
        movieList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val movie = Movie(
                movies[position].moviePoster,
                movies[position].movieTitle,
                movies[position].movieReleaseDate,
                movies[position].movieUserScore,
                movies[position].movieOverview
            )
            val i = Intent(this, MovieDetails::class.java)
            i.putExtra("EXTRA_MOVIE", movie)
            startActivity(i)
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
}
