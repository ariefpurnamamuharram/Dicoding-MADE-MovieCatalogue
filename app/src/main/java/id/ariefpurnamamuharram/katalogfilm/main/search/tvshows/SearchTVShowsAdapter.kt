package id.ariefpurnamamuharram.katalogfilm.main.search.tvshows

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.searchtvshows.SearchTVShows
import id.ariefpurnamamuharram.katalogfilm.details.TVShowsDetails

class SearchTVShowsAdapter(private var tvShows: MutableList<SearchTVShows>) :
    RecyclerView.Adapter<SearchTVShowsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchTVShowsViewHolder {
        return SearchTVShowsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_show, p0, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(p0: SearchTVShowsViewHolder, p1: Int) {
        p0.bindItem(tvShows[p1])
    }

}

class SearchTVShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvShowsTitle: TextView = view.findViewById(R.id.item_title)
    private val tvShowsReleaseDate: TextView = view.findViewById(R.id.item_release_date)
    private val tvShowsPoster: ImageView = view.findViewById(R.id.item_poster)

    fun bindItem(item: SearchTVShows) {
        val imageUrl = StringBuilder()
        imageUrl.append("https://image.tmdb.org/t/p/w185")
            .append(item.posterPath)

        tvShowsTitle.text = item.originalName
        tvShowsReleaseDate.text = item.firstAirDate

        Picasso.get().load(imageUrl.toString()).into(tvShowsPoster)

        itemView.setOnClickListener {
            Intent(itemView.context, TVShowsDetails::class.java).also { intent ->
                intent.putExtra("OBJ_TYPE", 2).also {
                    intent.putExtra("EXTRA_TV_SHOW", item).also {
                        intent.putExtra("TV_SHOW_POSTER_URL", imageUrl.toString()).also {
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}