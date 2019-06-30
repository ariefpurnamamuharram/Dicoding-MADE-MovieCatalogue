package id.ariefpurnamamuharram.katalogfilm.api.searchtvshows

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchTVShows(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("original_name")
    @Expose
    val originalName: String,

    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,

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