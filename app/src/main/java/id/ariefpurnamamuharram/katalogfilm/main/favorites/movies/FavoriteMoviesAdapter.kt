package id.ariefpurnamamuharram.katalogfilm.main.favorites.movies

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteMovie
import id.ariefpurnamamuharram.katalogfilm.details.MovieDetails

class FavoriteMoviesAdapter(private var movies: MutableList<FavoriteMovie>) :
    RecyclerView.Adapter<FavoriteMoviesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMoviesViewHolder {
        return FavoriteMoviesViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_show, p0, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: FavoriteMoviesViewHolder, p1: Int) {
        p0.bindItem(movies[p1])
    }
}

class FavoriteMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val movieTitle: TextView = view.findViewById(R.id.item_title)
    private val movieReleaseDate: TextView = view.findViewById(R.id.item_release_date)
    private val moviePoster: ImageView = view.findViewById(R.id.item_poster)

    fun bindItem(item: FavoriteMovie) {
        movieTitle.text = item.title
        movieReleaseDate.text = item.releaseDate

        Picasso.get().load(item.posterUrl.toString()).into(moviePoster)

        itemView.setOnClickListener {
            Intent(itemView.context, MovieDetails::class.java).also { intent ->
                intent.putExtra("OBJ_TYPE", 1).also {
                    intent.putExtra("EXTRA_MOVIE", item).also {
                        intent.putExtra("MOVIE_POSTER_URL", item.posterUrl).also {
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}