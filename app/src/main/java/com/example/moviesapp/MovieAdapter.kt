package com.example.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.databinding.MovieItemBinding
import kotlin.text.Typography.section


class MovieAdapter(private val movies: List<MovieListItem>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
        val movieAdult: TextView = itemView.findViewById(R.id.movie_adult)

        init {
            // Setup the click listener
            itemView.setOnClickListener {
                // Triggers click upwards to the adapter on click
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position)
                }
            }
        }

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
        val movieAdult = holder.movieAdult

        movieTitle.text = movie.title
        movieAdult.text = when (movie.isAdult) {
            true -> "Adult Film"
            false -> "Not Adult Film"
        }

        val movieUri = "https://image.tmdb.org/t/p/original" + movie.posterPath



        bindImage(movieImage, movieUri)
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View?, position: Int)
    }

    // Define listener member variable
    private lateinit var listener: OnItemClickListener

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


}