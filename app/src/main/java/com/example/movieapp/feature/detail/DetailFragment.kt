package com.example.movieapp.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var movieDetails: NestedScrollView
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        movieDetails = binding.detailsView

        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRating
        releaseDate = binding.movieReleaseDate
        overview = binding.movieOverview

        populateDetails(args)
        return binding.root
    }

    private fun populateDetails(args : DetailFragmentArgs) {
        args.movieDetail.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280${args.movieDetail.backdropPath}")
                .transform(CenterCrop())
                .into(backdrop)
        }

        args.movieDetail.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${args.movieDetail.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
        title.text = args.movieDetail.title.toString()
        rating.rating = args.movieDetail.rating / 2
        releaseDate.text = args.movieDetail.releaseDate.toString()
        overview.text = args.movieDetail.overview.toString()
    }
}