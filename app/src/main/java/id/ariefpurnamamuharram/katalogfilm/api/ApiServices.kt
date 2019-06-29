package id.ariefpurnamamuharram.katalogfilm.api

import id.ariefpurnamamuharram.katalogfilm.api.movies.MoviesResponse
import id.ariefpurnamamuharram.katalogfilm.api.tvshows.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("discover/movie?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getMovies(): Call<MoviesResponse>

    @GET("discover/tv?api_key=a7b525072e48cf9e235b340684cbe0db&language=en-US")
    fun getTvShows(): Call<TVShowsResponse>
}