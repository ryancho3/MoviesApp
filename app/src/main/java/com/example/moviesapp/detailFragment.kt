package com.example.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.detailFragmentArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val args = detailFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getMovieDetails(args.id)

        val movie = viewModel.movie.value
        // Inflate the layout for this fragment

        _binding = FragmentDetailBinding.inflate(layoutInflater)

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
        binding.movieRuntime.text = movie.runtime.toString()

        val movieUri = "https://image.tmdb.org/t/p/original" + movie.posterPath



        bindImage(binding.movieImage, movieUri)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }
}