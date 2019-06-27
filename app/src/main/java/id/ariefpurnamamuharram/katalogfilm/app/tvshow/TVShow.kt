package id.ariefpurnamamuharram.katalogfilm.app.tvshow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    val tvShowPoster: Int,
    val tvShowTitle: String,
    val tvShowReleaseDate: String,
    val tvShowUserScore: String,
    val tvShowOverview: String
) : Parcelable