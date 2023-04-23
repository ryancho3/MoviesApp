package com.example.moviesapp

import com.squareup.moshi.Json

data class ListResponse (
    @Json(name = "results")
    val results : List<MovieListItem>
    )

