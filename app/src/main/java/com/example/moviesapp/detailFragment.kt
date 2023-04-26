package com.example.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.detailFragmentArgs


class detailFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)

        val args = detailFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getMovieDetails(args.id)

        //val movie = viewModel.movie.value
        // Inflate the layout for this fragment

        viewModel.movie.observe(
            viewLifecycleOwner, Observer {
                val movie = it
                binding.movieTitle.text = movie?.title
                var genreText = ""
                for (genre in movie?.genres!!) {
                    genreText = genreText + genre.name + ", "
                }

                binding.movieGenre.text = genreText
                binding.movieDetails.text = movie.overview
                binding.movieAdult.text = when (movie.isAdult) {
                    true -> "Adult Film"
                    false -> "Not Adult Film"
                }
                binding.movieAvgRating.text = movie.voteAverage.toString()
                binding.movieRuntime.text = (movie.runtime / 60).toString() + " hour(s) " + (movie.runtime % 60).toString() + " minutes"

                val movieUri = "https://image.tmdb.org/t/p/original" + movie.posterPath



                bindImage(binding.movieImage, movieUri)
            }
        )

        return binding.root
    }


    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }
}