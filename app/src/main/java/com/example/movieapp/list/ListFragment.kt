package com.example.movieapp.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.data.movie.MoviesAdapter
import com.example.movieapp.databinding.FragmentListBinding
import com.example.movieapp.network.MoviesRepository


class ListFragment : Fragment() {

    private lateinit var listedMovies: RecyclerView
    private lateinit var listedMoviesAdapter: MoviesAdapter

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)

        listedMovies = binding.listedMovies
        listedMovies.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        listedMoviesAdapter = MoviesAdapter(listOf())
        listedMovies.adapter = listedMoviesAdapter
        MoviesRepository.getMovies(onSuccess = ::onListedMoviesFetched,
            onError = ::onError)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
    private fun onListedMoviesFetched(movies: List<Movie>) {
        listedMoviesAdapter.updateMovies(movies)
    }
    private fun onError() {
        Log.d("MainActivity", "Failed")
    }
}