package id.ariefpurnamamuharram.katalogfilm.api.tvshows

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("total_results")
    @Expose
    val total_results: Int,

    @SerializedName("total_pages")
    @Expose
    val total_pages: Int,

    @SerializedName("results")
    @Expose
    val results: ArrayList<TvShow>
)