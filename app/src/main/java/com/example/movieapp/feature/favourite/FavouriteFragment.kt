package com.example.movieapp.feature.favourite

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MainApp
import com.example.movieapp.databinding.FragmentFavouriteBinding
import com.example.movieapp.util.adapter.MoviesAdapter
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favouriteMovies: RecyclerView
    private lateinit var favouriteMoviesAdapter: MoviesAdapter
    private lateinit var favouriteMoviesLinearLayoutManager: LinearLayoutManager

    private val viewModel: FavouriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFavouriteBinding.inflate(inflater)
/*
        favouriteMovies = binding.favouriteMovies

        favouriteMovies.layoutManager = favouriteMoviesLinearLayoutManager
        favouriteMoviesAdapter = MoviesAdapter(mutableListOf())
        favouriteMovies.adapter = favouriteMoviesAdapter


        binding.viewModel = viewModel

        */
        binding.lifecycleOwner = this


        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApp.instance.appComponent.inject(this)
    }
}
