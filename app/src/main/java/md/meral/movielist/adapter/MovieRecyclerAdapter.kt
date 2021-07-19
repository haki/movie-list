package md.meral.movielist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.squareup.picasso.Picasso
import md.meral.movielist.R
import md.meral.movielist.util.Constants.ORIGINAL_POSTER_SIZE
import md.meral.movielist.model.Movie
import md.meral.movielist.model.MoviesResponse
import md.meral.movielist.view.MovieListFragmentDirections

class MovieRecyclerAdapter(val context: Context): RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    private lateinit var language: String
    var movieList: List<Movie> = listOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val poster: ImageView = view.findViewById(R.id.poster) // Poster
        private val title: TextView = view.findViewById(R.id.title) // Title
        private val language: TextView = view.findViewById(R.id.language) // Language
        private val voteAverage: TextView = view.findViewById(R.id.vote_average) // Vote Average
        private val voteCount: TextView = view.findViewById(R.id.vote_count) // Vote Count
        private val popularity: TextView = view.findViewById(R.id.popularity) // Popularty
        private val releaseDate: TextView = view.findViewById(R.id.release_date) // Release Date

        @SuppressLint("SetTextI18n", "ResourceType")
        fun setData(poster: String?, title: String?, language: String?, voteAverage: Double?, voteCount: Int?, popularity: Double?, releaseDate: String?) {

            //Picasso.get().load("$ORIGINAL_POSTER_SIZE${movie.posterPath}").into(holder.poster)
            Glide.with(itemView).load("$ORIGINAL_POSTER_SIZE${poster}").into(this.poster) // Poster
            this.title.text = title // Title
            this.language.text = "Language: $language" // Language
            this.voteAverage.text = "Vote Average: $voteAverage" // Vote Average
            this.voteCount.text = "Vote Count: $voteCount" // Vote Count
            this.popularity.text = "Popularity: $popularity" // Popularity
            this.releaseDate.text = "Release Date: $releaseDate" // Release Date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_movie_list_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movieList[position]

        holder.setData(movie.posterPath, movie.title, movie.originalLanguage, movie.voteAverage, movie.voteCount, movie.popularity, movie.releaseDate)

        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment("fromList", movie.id!!, language)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieListItems(body: MoviesResponse, language: String) {
        this.movieList = body.results
        this.language = language
        notifyDataSetChanged()
    }
}