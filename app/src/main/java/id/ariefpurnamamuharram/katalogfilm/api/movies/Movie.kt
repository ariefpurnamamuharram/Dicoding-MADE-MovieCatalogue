package id.ariefpurnamamuharram.katalogfilm.api.movies

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("vote_average")
    @Expose
    val vote_average: Float,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("poster_path")
    @Expose
    val poster_path: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("release_date")
    @Expose
    val release_date: String
) : Parcelable