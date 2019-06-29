package id.ariefpurnamamuharram.katalogfilm.main.tvshows

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShow
import id.ariefpurnamamuharram.katalogfilm.details.TVShowsDetails

class TVShowsAdapter(private var TVShows: MutableList<TVShow>) : RecyclerView.Adapter<TVShowsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TVShowsViewHolder {
        return TVShowsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_show, p0, false))
    }

    override fun getItemCount(): Int = TVShows.size

    override fun onBindViewHolder(p0: TVShowsViewHolder, p1: Int) {
        p0.bindItem(TVShows[p1])
    }
}

class TVShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvShowTitle: TextView = view.findViewById(R.id.item_title)
    private val tvShowReleaseDate: TextView = view.findViewById(R.id.item_release_date)
    private val tvShowPoster: ImageView = view.findViewById(R.id.item_poster)

    fun bindItem(item: TVShow) {
        val imageUrl = StringBuilder()
        imageUrl.append("https://image.tmdb.org/t/p/w185")
            .append(item.poster_path)

        tvShowTitle.text = item.original_name
        tvShowReleaseDate.text = item.first_air_date

        Picasso.get().load(imageUrl.toString()).into(tvShowPoster)

        itemView.setOnClickListener {
            Intent(itemView.context, TVShowsDetails::class.java).also { intent ->
                intent.putExtra("EXTRA_TV_SHOW", item).also {
                    intent.putExtra("TV_SHOW_POSTER_URL", imageUrl.toString()).also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }
}