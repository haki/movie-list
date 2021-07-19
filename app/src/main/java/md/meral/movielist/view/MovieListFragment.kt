package md.meral.movielist.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import md.meral.movielist.`interface`.ApiInterface
import md.meral.movielist.adapter.MovieRecyclerAdapter
import md.meral.movielist.databinding.FragmentMovieListBinding
import md.meral.movielist.model.Language
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieListFragment() : Fragment() {

    lateinit var language: String

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MovieRecyclerAdapter

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerAdapter = MovieRecyclerAdapter(this.requireContext())
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = recyclerAdapter

        getLanguages()

        Handler(Looper.getMainLooper()).postDelayed(this::getMovies, 1000)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    private fun getMovies() {

        val apiInterface = ApiInterface.create().getMovies(this.language)

        apiInterface.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response?.body() != null) {
                    recyclerAdapter.setMovieListItems(response.body()!!, language)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

            }
        })
    }

    private fun getLanguages() {

        val apiInterface = ApiInterface.createLanguage().getLanguages()

        apiInterface.enqueue(object : Callback<List<Language>> {
            override fun onResponse(
                call: Call<List<Language>>,
                response: Response<List<Language>>
            ) {

                if(response?.body() != null) {
                    setCurrentLanguage(response.body()!!.find { it.name == Locale.getDefault().displayLanguage }?.iso6391)
                }
            }

            override fun onFailure(call: Call<List<Language>>, t: Throwable) {}

        })
    }

    private fun setCurrentLanguage(iso6391: String?) {
        if (iso6391 != null) {
            language = iso6391
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}