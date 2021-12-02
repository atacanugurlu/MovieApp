package com.example.movieapp.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.MainApp
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

        lifecycleScope.launch(Dispatchers.IO) { populateDetails(args) }

        return binding.root


    }

    private suspend fun populateDetails(args: DetailFragmentArgs) {
        progressBar.visibility = View.VISIBLE

        withContext(Dispatchers.Main) {
            ImageLoader.provideGlide(requireContext(),
                "https://image.tmdb.org/t/p/w1280${args.movieDetail.backdropPath}",
                backdrop
            )}


        withContext(Dispatchers.Main) {
            ImageLoader.provideGlide(requireContext(),"https://image.tmdb.org/t/p/w342${args.movieDetail.posterPath}",
                poster)}

        withContext(Dispatchers.Main) {
            title.text = args.movieDetail.title.toString()
            rating.rating = args.movieDetail.rating / 2
            releaseDate.text = args.movieDetail.releaseDate.toString()
            overview.text = args.movieDetail.overview.toString()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApp.instance.appComponent.inject(this)
    }
}
