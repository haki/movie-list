package md.meral.movielist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.squareup.picasso.Picasso
import md.meral.movielist.R
import md.meral.movielist.util.Constants.ORIGINAL_POSTER_SIZE
import md.meral.movielist.model.Movie
import md.meral.movielist.model.MoviesResponse

class MovieRecyclerAdapter(val context: Context): RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    var movieList: List<Movie> = listOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val poster: ImageView = view.findViewById(R.id.poster)
        val title: TextView = view.findViewById(R.id.title)
        val language: TextView = view.findViewById(R.id.language)
        val voteAverage: TextView = view.findViewById(R.id.vote_average)
        val voteCount: TextView = view.findViewById(R.id.vote_count)
        val popularity: TextView = view.findViewById(R.id.popularity)
        val releaseDate: TextView = view.findViewById(R.id.release_date)

        val view = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_movie_list_row, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movieList[position]

        //Picasso.get().load("$ORIGINAL_POSTER_SIZE${movie.posterPath}").into(holder.poster)
        Glide.with(holder.view).load("$ORIGINAL_POSTER_SIZE${movie.posterPath}").into(holder.poster)
        holder.title.text = movie.originalTitle
        holder.language.text = "Original Language: ${movie.originalLanguage}"
        holder.voteAverage.text = "Vote Average: ${movie.voteAverage.toString()}"
        holder.voteCount.text = "Vote Count: ${movie.voteCount.toString()}"
        holder.popularity.text = "Popularity: ${movie.popularity.toString()}"
        holder.releaseDate.text = "Release Date: ${movie.releaseDate}"
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieListItems(body: MoviesResponse) {
        this.movieList = body.results
        notifyDataSetChanged()
    }
}