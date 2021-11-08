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
    private lateinit var listedMoviesLayoutManager: LinearLayoutManager

    private var listedMoviesPage = 1

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)

        listedMovies = binding.listedMovies

        listedMoviesLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        listedMovies.layoutManager = listedMoviesLayoutManager
        listedMoviesAdapter = MoviesAdapter(mutableListOf())
        listedMovies.adapter = listedMoviesAdapter
        getListedMovies()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
    private fun getListedMovies() {
        MoviesRepository.getMovies(
            listedMoviesPage,
            ::onListedMoviesFetched,
            ::onError
        )
    }
    private fun onListedMoviesFetched(movies: List<Movie>) {
        listedMoviesAdapter.appendMovies(movies)
        attachListedMoviesOnScrollListener()
    }
    private fun onError() {
        Log.d("List", "Failed")
    }

    private fun attachListedMoviesOnScrollListener() {
        listedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = listedMoviesLayoutManager.itemCount
                val visibleItemCount = listedMoviesLayoutManager.childCount
                val firstVisibleItem = listedMoviesLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    listedMovies.removeOnScrollListener(this)
                    listedMoviesPage++
                    getListedMovies()
                }
            }
        })
    }
}