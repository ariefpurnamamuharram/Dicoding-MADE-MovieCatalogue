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
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShow
import id.ariefpurnamamuharram.katalogfilm.db.db
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteTVShow
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TVShowsDetails : AppCompatActivity() {
    private lateinit var tvShow: TVShow
    private lateinit var tvShowPosterUrl: String

    private lateinit var tvShowTitle: TextView
    private lateinit var tvShowReleaseDate: TextView
    private lateinit var tvShowUserScore: TextView
    private lateinit var tvShowOverview: TextView
    private lateinit var tvShowPoster: ImageView

    private var objType: Int = 0

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        tvShowTitle = findViewById(R.id.show_title)
        tvShowReleaseDate = findViewById(R.id.show_release_date)
        tvShowUserScore = findViewById(R.id.show_user_score)
        tvShowOverview = findViewById(R.id.show_overview)
        tvShowPoster = findViewById(R.id.show_poster)

        objType = intent.getIntExtra("OBJ_TYPE", 0)
        when (objType) {
            0 -> {
                tvShow = intent.getParcelableExtra("EXTRA_TV_SHOW")
                tvShowPosterUrl = intent.getStringExtra("TV_SHOW_POSTER_URL")
            }
            1 -> {
                val result: FavoriteTVShow = intent.getParcelableExtra("EXTRA_TV_SHOW")
                tvShow = TVShow(
                    result.tvShowID.toString().toInt(),
                    result.originalName.toString(),
                    result.firstAirDate.toString(),
                    result.voteAverage.toString().toFloat(),
                    result.overview.toString(),
                    result.posterUrl.toString()
                )
                tvShowPosterUrl = intent.getStringExtra("TV_SHOW_POSTER_URL")
            }
        }

        // Set activity title.
        supportActionBar?.title = resources.getString(R.string.ativity_details_title_tv_show)

        // Check favorite state.
        favoriteState()

        tvShowTitle.text = tvShow.original_name
        tvShowReleaseDate.text = tvShow.first_air_date
        tvShowUserScore.text = tvShow.vote_average.toString()
        tvShowOverview.text = tvShow.overview

        Picasso.get().load(tvShowPosterUrl).into(tvShowPoster)
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
            val result = select(FavoriteTVShow.TABLE_FAVORITE_TV_SHOW).whereArgs(
                "ORIGINAL_NAME = {originalName}",
                "originalName" to tvShow.original_name
            )
            val parseItem = result.parseList(classParser<FavoriteTVShow>())
            if (parseItem.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(
                    FavoriteTVShow.TABLE_FAVORITE_TV_SHOW,
                    FavoriteTVShow.TV_SHOW_ID to tvShow.id.toString(),
                    FavoriteTVShow.ORIGINAL_NAME to tvShow.original_name,
                    FavoriteTVShow.FIRST_AIR_DATE to tvShow.first_air_date,
                    FavoriteTVShow.VOTE_AVERAGE to tvShow.vote_average.toString(),
                    FavoriteTVShow.OVERVIEW to tvShow.overview,
                    FavoriteTVShow.POSTER_URL to tvShowPosterUrl
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
                    FavoriteTVShow.TABLE_FAVORITE_TV_SHOW,
                    "ORIGINAL_NAME = {originalName}", "originalName" to tvShow.original_name
                )
            }
            Toast.makeText(this, resources.getString(R.string.removed_from_favorites), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
        }
    }
}