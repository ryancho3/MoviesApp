package com.example.moviesapp

import com.squareup.moshi.Json

data class MovieListItem (
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val isAdult: Boolean,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "title")
    val title: String
    )
