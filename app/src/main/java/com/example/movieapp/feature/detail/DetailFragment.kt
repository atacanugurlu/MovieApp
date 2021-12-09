package com.example.movieapp.feature.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.MainApp
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.util.ImageLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var movieDetails: NestedScrollView
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var favouriteToggle: ToggleButton


    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater)

        movieDetails = binding.detailsView

        progressBar = binding.progressBar
        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRating
        releaseDate = binding.movieReleaseDate
        overview = binding.movieOverview
        favouriteToggle = binding.detailFavouritesToggleButton


        lifecycleScope.launch(Dispatchers.Main) { populateDetails(args) }

        return binding.root

    }

    private suspend fun populateDetails(args: DetailFragmentArgs) {
        progressBar.visibility = View.VISIBLE

        lateinit var movie : Movie
        withContext(Dispatchers.IO){
            movie = viewModel.getMovieById(args.movieId)
        }

        favouriteToggle.isChecked = movie.isFavourite

        withContext(Dispatchers.Main) {
            ImageLoader.provideGlide(requireContext(),
                "https://image.tmdb.org/t/p/w1280${movie.backdropPath}",
                backdrop
            )}


        withContext(Dispatchers.Main) {
            ImageLoader.provideGlide(requireContext(),"https://image.tmdb.org/t/p/w342${movie.posterPath}",
                poster)}

        withContext(Dispatchers.Main) {
            title.text = movie.title.toString()
            rating.rating = movie.rating / 2
            releaseDate.text = movie.releaseDate.toString()
            overview.text = movie.overview.toString()
        }

        favouriteToggle.setOnClickListener {
            changeFavor(movie.id)
        }

    }

    private fun changeFavor(movieId: Long) {
        viewModel.changeMovieFavor(movieId)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApp.instance.appComponent.inject(this)
    }
}
