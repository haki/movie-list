package md.meral.movielist.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import md.meral.movielist.`interface`.ApiInterface
import md.meral.movielist.adapter.MovieDetailsRecyclerAdapter
import md.meral.movielist.databinding.FragmentMovieDetailsBinding
import md.meral.movielist.model.Genres
import md.meral.movielist.model.MovieDetails
import md.meral.movielist.util.Constants.ORIGINAL_POSTER_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsFragment() : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: MovieDetailsRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val receivedInfo = MovieDetailsFragmentArgs.fromBundle(it).message

            if (receivedInfo == "fromList") {

                recyclerView = binding.productionCompanies
                recyclerAdapter = MovieDetailsRecyclerAdapter(this.requireContext())

                linearLayoutManager = LinearLayoutManager(this.requireContext())
                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = recyclerAdapter

                getMovieDetails(MovieDetailsFragmentArgs.fromBundle(it).id, MovieDetailsFragmentArgs.fromBundle(it).language)
            } else {

                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieListFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    private fun getMovieDetails(id: Int, language: String) {

        val apiInterface = ApiInterface.create().getMovieDetails(id, language)

        apiInterface.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(
                call: Call<MovieDetails>,
                response: Response<MovieDetails>
            ) {
                if (response?.body() != null) {
                    setData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {

            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setData(body: MovieDetails) {

        Glide.with(binding.root).load("$ORIGINAL_POSTER_SIZE${body.posterPath}").into(binding.posterImage)
        binding.title.text = body.title
        binding.genres.text = ""
        binding.overview.text = body.overview

        binding.originalLanguage.text = "Language:\n${body.originalLanguage}"
        binding.releaseDate.text = "Release Date:\n${body.releaseDate}"

        binding.homepage.text = body.homepage
        binding.homepage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(body.homepage))
            startActivity(intent)
        }

        binding.budget.text = "Budget:\n$${body.budget}"
        binding.revenue.text = "Revenue:\n$${body.revenue}"
        binding.runtime.text = "Runtime:\n${body.runtime} Min"

        binding.voteCount.text = "Vote Count:\n${body.voteCount}"
        binding.popularity.text = "Popularity:\n${body.popularity}"
        binding.voteAverage.text = "Vote Average:\n${body.voteAverage}"

        recyclerAdapter.setCompanyInfo(body.productionCompanies)

        var i = 0
        for (genres: Genres in body.genres) {
            i++
            if (i != 1) {
                binding.genres.text = binding.genres.text as String + ", " + genres.name
            } else {
                binding.genres.text = genres.name
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}