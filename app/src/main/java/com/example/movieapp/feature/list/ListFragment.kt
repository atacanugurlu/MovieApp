package com.example.movieapp.feature.list

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MainApp
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.util.adapter.MoviesAdapter
import com.example.movieapp.databinding.FragmentListBinding
import java.util.*
import javax.inject.Inject


class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listedMovies: RecyclerView
    private lateinit var listedMoviesAdapter: MoviesAdapter
    private lateinit var listedMoviesLinearLayoutManager: LinearLayoutManager
    private lateinit var listedMoviesGridLayoutManager: GridLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView


    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)

        listedMovies = binding.listedMovies
        progressBar = binding.progressBar
        searchView = binding.searchView


        viewModel.getAllMovies().observe(viewLifecycleOwner) { moviesList ->
            progressBar.visibility = View.VISIBLE
            listedMoviesAdapter.submitList(moviesList)
            attachListedMoviesOnScrollListener()
            progressBar.visibility = View.GONE
        }

        viewModel.getListedMovies()


        listedMoviesLinearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        //Starts as linear Layout
        listedMovies.layoutManager = listedMoviesLinearLayoutManager
        listedMoviesAdapter = MoviesAdapter() { movieId -> changeFavor(movieId) }
        listedMovies.adapter = listedMoviesAdapter


        //Grid Layout
        binding.gridButton.setOnClickListener {
            listedMoviesGridLayoutManager = GridLayoutManager(activity, 3)
            listedMovies.layoutManager = listedMoviesGridLayoutManager
        }
        //Linear Layout
        binding.listButton.setOnClickListener {
            listedMovies.layoutManager = listedMoviesLinearLayoutManager
            listedMovies.adapter = listedMoviesAdapter
        }

        //Search view
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if(newText.length > 2){
                        viewModel.searchMovies().observe(viewLifecycleOwner){movies->
                            Log.i("dasdasdff","${movies[0].title}")
                        }
                    }
                }
                return true
            }



        })




        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    private fun changeFavor(movieId: Long) {
        viewModel.changeMovieFavor(movieId)
    }

    private fun attachListedMoviesOnScrollListener() {
        listedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = listedMoviesLinearLayoutManager.itemCount
                val visibleItemCount = listedMoviesLinearLayoutManager.childCount
                val firstVisibleItem =
                    listedMoviesLinearLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    listedMovies.removeOnScrollListener(this)
                    viewModel.listedMoviesPage++
                    Log.i("Page", "${viewModel.listedMoviesPage}")
                    viewModel.getListedMovies()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApp.instance.appComponent.inject(this)
    }


}