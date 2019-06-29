package id.ariefpurnamamuharram.katalogfilm.main.favorites.tvshows

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteTVShow
import id.ariefpurnamamuharram.katalogfilm.details.TVShowsDetails

class FavoriteTVShowsAdapter(private var tvShows: MutableList<FavoriteTVShow>) :
    RecyclerView.Adapter<FavoriteTVShowsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteTVShowsViewHolder {
        return FavoriteTVShowsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_show, p0, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(p0: FavoriteTVShowsViewHolder, p1: Int) {
        p0.bindItem(tvShows[p1])
    }
}

class FavoriteTVShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvShowTitle: TextView = view.findViewById(R.id.item_title)
    private val tvShowReleaseDate: TextView = view.findViewById(R.id.item_release_date)
    private val tvShowPoster: ImageView = view.findViewById(R.id.item_poster)

    fun bindItem(item: FavoriteTVShow) {
        tvShowTitle.text = item.originalName
        tvShowReleaseDate.text = item.firstAirDate

        Picasso.get().load(item.posterUrl.toString()).into(tvShowPoster)

        itemView.setOnClickListener {
            Intent(itemView.context, TVShowsDetails::class.java).also { intent ->
                intent.putExtra("OBJ_TYPE", 1).also {
                    intent.putExtra("EXTRA_TV_SHOW", item).also {
                        intent.putExtra("TV_SHOW_POSTER_URL", item.posterUrl). also {
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}