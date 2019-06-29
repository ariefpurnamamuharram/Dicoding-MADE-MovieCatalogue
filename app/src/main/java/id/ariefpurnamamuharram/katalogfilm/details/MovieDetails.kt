package id.ariefpurnamamuharram.katalogfilm.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.movies.Movie

class MovieDetails : AppCompatActivity() {
    private lateinit var movie: Movie

    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieUserScore: TextView
    private lateinit var movieOverview: TextView
    private lateinit var moviePoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        movieTitle = findViewById(R.id.show_title)
        movieReleaseDate = findViewById(R.id.show_release_date)
        movieUserScore = findViewById(R.id.show_user_score)
        movieOverview = findViewById(R.id.show_overview)
        moviePoster = findViewById(R.id.show_poster)

        // Retrieve parcelable object.
        movie = intent.getParcelableExtra("EXTRA_MOVIE")

        // Set activity title.
        supportActionBar?.title = resources.getString(R.string.ativity_details_title_movie)

        // Poster image URL.
        val imageUrl = StringBuilder()
        imageUrl.append("https://image.tmdb.org/t/p/w185")
            .append(movie.poster_path)

        movieTitle.text = movie.title
        movieReleaseDate.text = movie.release_date
        movieUserScore.text = movie.vote_average.toString()
        movieOverview.text = movie.overview

        Picasso.get().load(imageUrl.toString()).into(moviePoster)
    }
}