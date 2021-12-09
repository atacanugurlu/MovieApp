package com.example.movieapp.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.databinding.GridItemMovieBinding
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.feature.pager.PagerFragmentDirections
import com.example.movieapp.util.ImageLoader
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private var movies: MutableList<Movie>,
    private val onFavouritesButtonClick: (Long) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }

    inner class MovieViewHolder(
        val binding: ListItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            ImageLoader.provideGlide(
                itemView.context,
                "https://image.tmdb.org/t/p/w342${movie.posterPath}",
                binding.itemMoviePoster
            )

            binding.itemMovieName.text = movie.title
            binding.itemMovieRating.text = movie.rating.toString()
            binding.itemMovieYear.text = when (movie.releaseDate?.length) {
                in 0..3 -> "N/A"
                else -> movie.releaseDate?.substring(0, 4)
            }
            val toggleButton = binding.mainFavouritesToggleButton

            if(movie.isFavourite){
                toggleButton.isChecked = true
            }

            toggleButton.setOnClickListener {
                onFavouritesButtonClick(movie.id)
            }
            navigateToDetails(itemView, movie)
        }
    }
/*
    class GridViewHolder private constructor(val binding: GridItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {
            ImageLoader.provideGlide(
                itemView.context,
                "https://image.tmdb.org/t/p/w342${movie.posterPath}",
                binding.itemMoviePoster
            )

            // Navigate to detail fragment
            navigateToDetails(itemView, movie)
        }

        companion object {
            fun from(parent: ViewGroup): GridViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridItemMovieBinding.inflate(layoutInflater, parent, false)
                return GridViewHolder(binding)
            }
        }
    }*/
}


private fun navigateToDetails(itemView: View, movie: Movie) {

    itemView.setOnClickListener {
        val action: NavDirections =
            PagerFragmentDirections.actionPagerFragmentToDetailFragment(movie)
        findNavController(itemView).navigate(action)
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}