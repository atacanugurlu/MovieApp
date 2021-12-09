package com.example.movieapp.feature.favourite

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MainApp
import com.example.movieapp.databinding.FragmentFavouriteBinding
import com.example.movieapp.util.adapter.FavouritesAdapter
import com.example.movieapp.util.adapter.MoviesAdapter
import javax.inject.Inject
import kotlin.math.log

class FavouriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favouriteMovies: RecyclerView
    private lateinit var favouriteMoviesAdapter: FavouritesAdapter
    private lateinit var favouriteMoviesLinearLayoutManager: LinearLayoutManager
    private lateinit var favouriteMoviesGridLayoutManager: GridLayoutManager
    private lateinit var progressBar: ProgressBar


    private val viewModel: FavouriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFavouriteBinding.inflate(inflater)

        favouriteMovies = binding.favouriteMovies
        progressBar = binding.progressBar

        viewModel.getFavouriteMovies().observe(viewLifecycleOwner) {moviesList ->
            progressBar.visibility = View.VISIBLE
            favouriteMoviesAdapter.appendMovies(moviesList)
            attachListedMoviesOnScrollListener()
            progressBar.visibility = View.GONE
        }



        favouriteMoviesLinearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        favouriteMovies.layoutManager = favouriteMoviesLinearLayoutManager
        favouriteMoviesAdapter = FavouritesAdapter(mutableListOf()) {movieId -> changeFavor(movieId)}
        favouriteMovies.adapter = favouriteMoviesAdapter



        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
    private fun changeFavor(movieId: Long) {
        viewModel.changeMovieFavor(movieId)
    }
    private fun attachListedMoviesOnScrollListener() {
        favouriteMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = favouriteMoviesLinearLayoutManager.itemCount
                val visibleItemCount = favouriteMoviesLinearLayoutManager.childCount
                val firstVisibleItem =
                    favouriteMoviesLinearLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    favouriteMovies.removeOnScrollListener(this)
                    viewModel.favouriteMoviesPage++
                    viewModel.getFavouriteMovies()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApp.instance.appComponent.inject(this)
    }
}
