package com.example.movieapp.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.R
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.databinding.GridItemMovieBinding
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.feature.list.ListFragmentDirections
import javax.inject.Inject

class MoviesAdapter(
    private var movies: MutableList<Movie>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
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


    class MovieViewHolder @Inject constructor(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(binding.itemMoviePoster)
            binding.itemMovieName.text = movie.title
            binding.itemMovieRating.text = movie.rating.toString()
            binding.itemMovieYear.text = when(movie.releaseDate?.length){
                in 0..3 -> "N/A"
                else -> movie.releaseDate?.substring(0, 4)
            }
          //  binding.itemMovieYear.text = movie.releaseDate.substring(0, 4)

            // Navigate to detail fragment
            navigateToDetails(itemView, movie)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }

    }

    class GridViewHolder private constructor(val binding: GridItemMovieBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(binding.itemMoviePoster)

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
    }
}

private fun navigateToDetails(itemView: View, movie : Movie){

    itemView.setOnClickListener{
        val action: NavDirections = ListFragmentDirections.actionListFragmentToDetailFragment(movie)
        findNavController(itemView).navigate(action)
       // Navigation.createNavigateOnClickListener(R.id.action_listFragment_to_detailFragment)
    }
}

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}