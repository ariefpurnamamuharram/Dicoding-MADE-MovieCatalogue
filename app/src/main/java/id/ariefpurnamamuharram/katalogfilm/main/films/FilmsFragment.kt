package id.ariefpurnamamuharram.katalogfilm.main.films

import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.app.film.Film

class FilmsFragment : Fragment() {
    private lateinit var filmTitle: Array<String>
    private lateinit var filmReleaseDate: Array<String>
    private lateinit var filmUserScore: Array<String>
    private lateinit var filmOverview: Array<String>
    private lateinit var filmPoster: TypedArray

    private lateinit var rootView: View
    private lateinit var rvFilms: RecyclerView

    private var films: ArrayList<Film> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_films, container, false)

        rvFilms = rootView.findViewById(R.id.rv_films)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Prepare films data.
        prepare()
        addItem()

        // Setup movie RecyclerView.
        rvFilms.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FilmsAdapter(films)
        }
    }

    private fun addItem() {
        var i = 0
        while (i < filmTitle.size) {
            films.add(
                i, Film(
                    filmPoster.getResourceId(i, -1),
                    filmTitle[i],
                    filmReleaseDate[i],
                    filmUserScore[i],
                    filmOverview[i]
                )
            )
            i++
        }
    }

    private fun prepare() {
        filmPoster = resources.obtainTypedArray(R.array.tv_poster)
        filmTitle = resources.getStringArray(R.array.tv_title)
        filmReleaseDate = resources.getStringArray(R.array.tv_release_date)
        filmUserScore = resources.getStringArray(R.array.tv_user_score)
        filmOverview = resources.getStringArray(R.array.tv_overview)
    }


    companion object {
        fun newInstance(): FilmsFragment = FilmsFragment()
    }
}