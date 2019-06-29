package id.ariefpurnamamuharram.katalogfilm.api.tvshows

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("original_name")
    @Expose
    val original_name: String,

    @SerializedName("first_air_date")
    @Expose
    val first_air_date: String,

    @SerializedName("vote_average")
    @Expose
    val vote_average: Float,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("poster_path")
    @Expose
    val poster_path: String
) : Parcelable