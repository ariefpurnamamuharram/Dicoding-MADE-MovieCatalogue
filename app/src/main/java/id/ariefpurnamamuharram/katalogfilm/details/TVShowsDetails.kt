package id.ariefpurnamamuharram.katalogfilm.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TvShow

class TVShowsDetails : AppCompatActivity() {
    private lateinit var tvShow: TvShow

    private lateinit var tvShowTitle: TextView
    private lateinit var tvShowReleaseDate: TextView
    private lateinit var tvShowUserScore: TextView
    private lateinit var tvShowOverview: TextView
    private lateinit var tvShowPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        tvShowTitle = findViewById(R.id.show_title)
        tvShowReleaseDate = findViewById(R.id.show_release_date)
        tvShowUserScore = findViewById(R.id.show_user_score)
        tvShowOverview = findViewById(R.id.show_overview)
        tvShowPoster = findViewById(R.id.show_poster)

        // Retrieve parcelable object.
        tvShow = intent.getParcelableExtra("EXTRA_TV_SHOW")

        // Set activity title.
        supportActionBar?.title = resources.getString(R.string.ativity_details_title_tv_show)

        // Poster image URL.
        val imageUrl = StringBuilder()
        imageUrl.append("https://image.tmdb.org/t/p/w185")
            .append(tvShow.poster_path)

        tvShowTitle.text = tvShow.original_name
        tvShowReleaseDate.text = tvShow.first_air_date
        tvShowUserScore.text = tvShow.vote_average.toString()
        tvShowOverview.text = tvShow.overview

        Picasso.get().load(imageUrl.toString()).into(tvShowPoster)
    }
}