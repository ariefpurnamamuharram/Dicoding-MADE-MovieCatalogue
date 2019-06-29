package id.ariefpurnamamuharram.katalogfilm.main.movies

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.api.movies.Movie
import id.ariefpurnamamuharram.katalogfilm.details.MovieDetails

class MoviesAdapter(private var movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_show, p0, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: MoviesViewHolder, p1: Int) {
        p0.bindItem(movies[p1])
    }
}

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val movieTitle: TextView = view.findViewById(R.id.item_title)
    private val movieReleaseDate: TextView = view.findViewById(R.id.item_release_date)
    private val moviePoster: ImageView = view.findViewById(R.id.item_poster)

    fun bindItem(item: Movie) {
        val imageUrl = StringBuilder()
        imageUrl.append("https://image.tmdb.org/t/p/w185")
            .append(item.poster_path)

        movieTitle.text = item.title
        movieReleaseDate.text = item.release_date

        Picasso.get().load(imageUrl.toString()).into(moviePoster)

        itemView.setOnClickListener {
            Intent(itemView.context, MovieDetails::class.java).also { intent ->
                intent.putExtra("EXTRA_MOVIE", item).also {
                    intent.putExtra("MOVIE_POSTER_URL", imageUrl.toString()).also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }
}