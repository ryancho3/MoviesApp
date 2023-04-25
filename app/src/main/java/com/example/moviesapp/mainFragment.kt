package com.example.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.moviesapp.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainFragment : Fragment() {


    private lateinit var viewModel: MovieViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        _binding = FragmentMainBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)


        val recyclerView = binding.movieListRv
        val adapter = viewModel.movies?.value?.let { MovieAdapter(it) }
        recyclerView.adapter = adapter


        viewModel.movies.observe(viewLifecycleOwner, Observer {
            val adapter = MovieAdapter(it)
                recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
                override fun onItemClick(itemView: View?, position: Int) {
                    val movieID = viewModel.movies.value?.get(position)?.id
                    val bundle = bundleOf("id" to movieID)
                    view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                }
            })

            })


        adapter?.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View?, position: Int) {
                val movieID = viewModel.movies.value?.get(position)?.id
                val bundle = bundleOf("id" to movieID)
                view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            }
        })

        return binding.root
    }


}