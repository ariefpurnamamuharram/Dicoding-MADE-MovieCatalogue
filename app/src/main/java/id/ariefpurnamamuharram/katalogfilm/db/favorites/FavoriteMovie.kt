package id.ariefpurnamamuharram.katalogfilm.db.favorites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMovie(
    val id: Long?,
    val movieID: String?,
    val title: String?,
    val releaseDate: String?,
    val voteAverage: String?,
    val overview: String?,
    val posterUrl: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE_MOVIE: String = "TABLE_FAVORITE_MOVIE"
        const val ID: String = "ID_"
        const val MOVIE_ID: String = "MOVIE_ID"
        const val TITLE: String = "TITLE"
        const val RELEASE_DATE: String = "RELEASE_DATE"
        const val VOTE_AVERAGE: String = "VOTE_AVERAGE"
        const val OVERVIEW: String = "OVERVIEW"
        const val POSTER_URL: String = "POSTER_URL"
    }
}