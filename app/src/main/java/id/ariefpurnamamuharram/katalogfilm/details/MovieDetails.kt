package id.ariefpurnamamuharram.katalogfilm.details

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.movies.Movie
import id.ariefpurnamamuharram.katalogfilm.db.db
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteMovie
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MovieDetails : AppCompatActivity() {
    private lateinit var movie: Movie
    private lateinit var moviePosterUrl: String

    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieUserScore: TextView
    private lateinit var movieOverview: TextView
    private lateinit var moviePoster: ImageView

    private var objType: Int = 0

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        movieTitle = findViewById(R.id.show_title)
        movieReleaseDate = findViewById(R.id.show_release_date)
        movieUserScore = findViewById(R.id.show_user_score)
        movieOverview = findViewById(R.id.show_overview)
        moviePoster = findViewById(R.id.show_poster)

        objType = intent.getIntExtra("OBJ_TYPE", 0)
        when (objType) {
            0 -> {
                movie = intent.getParcelableExtra("EXTRA_MOVIE")
                moviePosterUrl = intent.getStringExtra("MOVIE_POSTER_URL")
            }
            1 -> {
                val result: FavoriteMovie = intent.getParcelableExtra("EXTRA_MOVIE")
                movie = Movie(
                    result.movieID.toString().toInt(),
                    result.voteAverage.toString().toFloat(),
                    result.title.toString(),
                    result.posterUrl.toString(),
                    result.overview.toString(),
                    result.releaseDate.toString()
                )
                moviePosterUrl = intent.getStringExtra("MOVIE_POSTER_URL")
            }
        }

        // Set activity title.
        supportActionBar?.title = resources.getString(R.string.ativity_details_title_movie)

        // Check favorite state.
        favoriteState()

        movieTitle.text = movie.title
        movieReleaseDate.text = movie.release_date
        movieUserScore.text = movie.vote_average.toString()
        movieOverview.text = movie.overview

        Picasso.get().load(moviePosterUrl).into(moviePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_starred)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
    }

    private fun favoriteState() {
        db.use {
            val result = select(FavoriteMovie.TABLE_FAVORITE_MOVIE).whereArgs(
                "TITLE = {title}",
                "title" to movie.title
            )
            val parseItem = result.parseList(classParser<FavoriteMovie>())
            if (parseItem.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(
                    FavoriteMovie.TABLE_FAVORITE_MOVIE,
                    FavoriteMovie.MOVIE_ID to movie.id.toString(),
                    FavoriteMovie.TITLE to movie.title,
                    FavoriteMovie.RELEASE_DATE to movie.release_date,
                    FavoriteMovie.VOTE_AVERAGE to movie.vote_average.toString(),
                    FavoriteMovie.OVERVIEW to movie.overview,
                    FavoriteMovie.POSTER_URL to moviePosterUrl
                )
            }
            Toast.makeText(this, resources.getString(R.string.added_to_favorites), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun removeFromFavorite() {
        try {
            db.use {
                delete(
                    FavoriteMovie.TABLE_FAVORITE_MOVIE,
                    "TITLE = {title}", "title" to movie.title
                )
            }
            Toast.makeText(this, resources.getString(R.string.removed_from_favorites), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
        }
    }
}