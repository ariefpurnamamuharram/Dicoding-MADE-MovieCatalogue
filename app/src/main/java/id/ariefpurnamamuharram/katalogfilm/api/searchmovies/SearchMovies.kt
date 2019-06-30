package id.ariefpurnamamuharram.katalogfilm.api.searchmovies

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchMovies(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String
) : Parcelable