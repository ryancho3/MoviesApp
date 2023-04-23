package com.example.moviesapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    private val _movies = MutableLiveData<List<MovieListItem>>()
    val movies : LiveData<List<MovieListItem>>
        get() = _movies

    private val _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie>
        get() = _movie

    init {
        getNowPlaying()
        Log.i("MOVIE_API_INIT", _movies.value.toString())
    }

    fun getNowPlaying() {
        MovieApi.retrofitService.getNowPlaying().enqueue(object: Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                val results = response.body()!!.results.toList()
                _movies.value = results.toList()
            }
            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.i("MOVIE_API", "ERROR: " + t.message + ", getNowPlaying")
            }
        })
    }

    fun getMovieDetails(id: Int) {
        MovieApi.retrofitService.getDetails(id.toString()).enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                _movie.value = response.body()
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("MOVIE_API", "ERROR: " + t.message + ", getMovieDetail")
            }
        })
    }
}