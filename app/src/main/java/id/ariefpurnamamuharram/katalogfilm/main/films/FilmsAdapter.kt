package id.ariefpurnamamuharram.katalogfilm.main.films

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.film.Film
import id.ariefpurnamamuharram.katalogfilm.details.FilmDetails

class FilmsAdapter(private var films: MutableList<Film>) : RecyclerView.Adapter<FilmsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FilmsViewHolder {
        return FilmsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_film, p0, false))
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(p0: FilmsViewHolder, p1: Int) {
        p0.bindItem(films[p1])
    }
}

class FilmsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val filmTitle: TextView = view.findViewById(R.id.film_title)
    private val filmReleaseDate: TextView = view.findViewById(R.id.film_release_date)
    private val filmPoster: ImageView = view.findViewById(R.id.film_poster)

    fun bindItem(item: Film) {
        filmTitle.text = item.filmTitle
        filmReleaseDate.text = item.filmReleaseDate

        Picasso.get().load(item.filmPoster).into(filmPoster)

        itemView.setOnClickListener {
            Intent(itemView.context, FilmDetails::class.java).also { intent ->
                intent.putExtra("EXTRA_FILM", item).also {
                    itemView.context.startActivity(it)
                }
            }
        }
    }
}