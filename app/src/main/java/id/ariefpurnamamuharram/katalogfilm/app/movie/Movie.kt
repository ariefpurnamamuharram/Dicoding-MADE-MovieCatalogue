package id.ariefpurnamamuharram.katalogfilm.app.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val moviePoster: Int,
    val movieTitle: String,
    val movieReleaseDate: String,
    val movieUserScore: String,
    val movieOverview: String
) : Parcelable