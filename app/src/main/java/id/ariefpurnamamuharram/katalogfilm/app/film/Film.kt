package id.ariefpurnamamuharram.katalogfilm.app.film

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val filmPoster: Int,
    val filmTitle: String,
    val filmReleaseDate: String,
    val filmUserScore: String,
    val filmOverview: String
) : Parcelable