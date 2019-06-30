package id.ariefpurnamamuharram.katalogfilm.api

import id.ariefpurnamamuharram.katalogfilm.api.movies.MoviesResponse
import id.ariefpurnamamuharram.katalogfilm.api.searchmovies.SearchMoviesResponse
import id.ariefpurnamamuharram.katalogfilm.api.searchtvshows.SearchTVShowsResponse
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("discover/movie?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getMovies(): Call<MoviesResponse>

    @GET("discover/tv?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getTvShows(): Call<TVShowsResponse>

    @GET("search/movie?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getSearchMovies(@Query("query") query: String): Call<SearchMoviesResponse>

    @GET("https://api.themoviedb.org/3/search/tv?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getSearchTVShows(@Query("query") query: String): Call<SearchTVShowsResponse>
}