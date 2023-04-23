package com.example.moviesapp

import com.squareup.moshi.Json

data class Movie (
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "poster_path")
    val posterPath : String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "genres")
    val genres: MutableList<Genre>,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "adult")
    val isAdult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
)