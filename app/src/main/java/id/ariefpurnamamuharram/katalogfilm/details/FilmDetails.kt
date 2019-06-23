package id.ariefpurnamamuharram.katalogfilm.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.film.Film

class FilmDetails : AppCompatActivity() {
    private lateinit var film: Film

    private lateinit var filmTitle: TextView
    private lateinit var filmReleaseDate: TextView
    private lateinit var filmUserScore: TextView
    private lateinit var filmOverview: TextView
    private lateinit var filmPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        filmTitle = findViewById(R.id.film_title)
        filmReleaseDate = findViewById(R.id.film_release_date)
        filmUserScore = findViewById(R.id.film_user_score)
        filmOverview = findViewById(R.id.film_overview)
        filmPoster = findViewById(R.id.film_poster)

        // Retrieve parcelable object.
        film = intent.getParcelableExtra("EXTRA_FILM")

        filmTitle.text = film.filmTitle
        filmReleaseDate.text = film.filmReleaseDate
        filmUserScore.text = film.filmUserScore
        filmOverview.text = film.filmOverview

        Picasso.get().load(film.filmPoster).into(filmPoster)
    }
}