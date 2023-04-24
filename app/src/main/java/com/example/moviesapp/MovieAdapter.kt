package com.example.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieItemBinding
import kotlin.text.Typography.section


class MovieAdapter(private val movies: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
        val movieAdult: TextView = itemView.findViewById(R.id.movie_adult)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.movie_item, parent, false)


        return MovieViewHolder(view)    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val movieImage = holder.movieImage
        val movieTitle = holder.movieTitle
        val movieGenre = holder.movieGenre
        val movieAdult = holder.movieAdult

        movieTitle.text = movie.title
        movieGenre.text = "IMPLEMENT GENRE"
        movieAdult.text = when (movie.isAdult) {
            true -> "R-18"
            false -> "Not R-18"
        }
        movieImage.setImageResource(0)

    }


}