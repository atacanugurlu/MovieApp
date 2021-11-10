package com.example.movieapp.feature.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.movie.MoviesAdapter
import com.example.movieapp.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var listedMovies: RecyclerView
    private lateinit var listedMoviesAdapter: MoviesAdapter
    private lateinit var listedMoviesLayoutManager: LinearLayoutManager


    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)

        listedMovies = binding.listedMovies

        viewModel.movieData.observe(viewLifecycleOwner){ moviesList ->
            listedMoviesAdapter.appendMovies(moviesList)
            //listedMoviesAdapter.submitList(moviesList)
            attachListedMoviesOnScrollListener()

        }


        viewModel.getListedMovies()

        listedMoviesLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        listedMovies.layoutManager = listedMoviesLayoutManager
        listedMoviesAdapter = MoviesAdapter(mutableListOf())
        listedMovies.adapter = listedMoviesAdapter

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

            return binding.root
    }

    private fun attachListedMoviesOnScrollListener() {
        listedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = listedMoviesLayoutManager.itemCount
                val visibleItemCount = listedMoviesLayoutManager.childCount
                val firstVisibleItem = listedMoviesLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    listedMovies.removeOnScrollListener(this)
                    viewModel.listedMoviesPage++
                    viewModel.getListedMovies()
                }
            }
        })
    }

}