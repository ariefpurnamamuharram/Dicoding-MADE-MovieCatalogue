package id.ariefpurnamamuharram.katalogfilm.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.movie.Movie

class MovieListAdapter(
    private val context: Context,
    private val movies: ArrayList<Movie>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.item_movie, parent, false)

        // Get movie title element
        val movieTitle = rootView.findViewById<TextView>(R.id.movie_title)

        // Get movie release date element
        val movieReleaseDate = rootView.findViewById<TextView>(R.id.movie_release_date)

        // Get movie poster element
        val moviePoster = rootView.findViewById<ImageView>(R.id.movie_poster)

        val movie = getItem(position) as Movie

        movieTitle.text = movie.movieTitle
        movieReleaseDate.text = movie.movieReleaseDate

        Picasso.get().load(movie.moviePoster).into(moviePoster)

        return rootView
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }
}