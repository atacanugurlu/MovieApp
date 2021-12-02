package com.example.movieapp.util.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.feature.favourite.FavouriteFragmentDirections
import com.example.movieapp.util.ImageLoader
import javax.inject.Inject

class FavouritesAdapter @Inject constructor(
    private var favouriteMovies: MutableList<Movie>
) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder.from(parent)
    }

    override fun getItemCount(): Int = favouriteMovies.size

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val item = favouriteMovies[position]
        holder.bind(item)
    }

    fun appendMovies(movies: List<Movie>?) {
        if (movies != null) {
            this.favouriteMovies.addAll(movies)
        }
        if (movies != null) {
            notifyItemRangeInserted(
                this.favouriteMovies.size,
                movies.size - 1
            )
        }
    }


    class FavouritesViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
            binding.mainFavouritesToggleButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    movie.isFavourite = true
                    Log.i("button", "${movie.isFavourite}")
                } else {
                    movie.isFavourite = false
                    Log.i("button", "${movie.isFavourite}")

                }
            }

            navigateToDetails(itemView, movie)
        }

        companion object {
            fun from(parent: ViewGroup): FavouritesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
                return FavouritesViewHolder(binding)
            }
        }
    }
}

private fun navigateToDetails(itemView: View, movie: Movie) {

    itemView.setOnClickListener {
        val action: NavDirections =
            FavouriteFragmentDirections.actionFavouriteFragmentToDetailFragment(movie)
        Navigation.findNavController(itemView).navigate(action)
    }
}

class FavouriteDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}