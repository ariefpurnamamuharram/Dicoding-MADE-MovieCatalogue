package id.ariefpurnamamuharram.katalogfilm.db.favorites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTVShow(
    val id: Long?,
    val tvShowID: String?,
    val originalName: String?,
    val firstAirDate: String?,
    val voteAverage: String?,
    val overview: String?,
    val posterUrl: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE_TV_SHOW: String = "TABLE_FAVORITE_TV_SHOW"
        const val ID = "ID_"
        const val TV_SHOW_ID: String = "TV_SHOW_ID"
        const val ORIGINAL_NAME: String = "ORIGINAL_NAME"
        const val FIRST_AIR_DATE: String = "FIRST_AIR_DATE"
        const val VOTE_AVERAGE: String = "VOTE_AVERAGE"
        const val OVERVIEW: String = "OVERVIEW"
        const val POSTER_URL: String = "POSTER_URL"
    }
}