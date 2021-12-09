package com.example.movieapp.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.feature.pager.PagerFragmentDirections
import com.example.movieapp.util.ImageLoader
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private val onFavouritesButtonClick: (Long) -> Unit
) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
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

            toggleButton.isChecked = movie.isFavourite

            toggleButton.setOnClickListener {
                onFavouritesButtonClick(movie.id)
            }
            navigateToDetails(itemView, movie.id)
        }
    }
}


private fun navigateToDetails(itemView: View, movieId: Long) {

    itemView.setOnClickListener {
        val action: NavDirections =
            PagerFragmentDirections.actionPagerFragmentToDetailFragment(movieId)
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