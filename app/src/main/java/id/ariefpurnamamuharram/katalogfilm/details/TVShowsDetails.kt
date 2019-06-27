package id.ariefpurnamamuharram.katalogfilm.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.tvshow.TVShow

class TVShowsDetails : AppCompatActivity() {
    private lateinit var tvShow: TVShow

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

        tvShowTitle.text = tvShow.tvShowTitle
        tvShowReleaseDate.text = tvShow.tvShowReleaseDate
        tvShowUserScore.text = tvShow.tvShowUserScore
        tvShowOverview.text = tvShow.tvShowOverview

        Picasso.get().load(tvShow.tvShowPoster).into(tvShowPoster)
    }
}