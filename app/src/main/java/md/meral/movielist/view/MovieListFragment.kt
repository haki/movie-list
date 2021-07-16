package md.meral.movielist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import md.meral.movielist.R
import md.meral.movielist.`interface`.ApiInterface
import md.meral.movielist.adapter.MovieRecyclerAdapter
import md.meral.movielist.databinding.FragmentMovieDetailsBinding
import md.meral.movielist.databinding.FragmentMovieListBinding
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MovieRecyclerAdapter

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        recyclerView = binding.recyclerView
        recyclerAdapter = MovieRecyclerAdapter(this.requireContext())
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = recyclerAdapter

        getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMovies() {
        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response?.body() != null) {
                    recyclerAdapter.setMovieListItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

            }
        })
    }
}