package md.meral.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import md.meral.movielist.`interface`.ApiInterface
import md.meral.movielist.adapter.MovieRecyclerAdapter
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerAdapter = MovieRecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        getMovies()
    }

    private fun getMovies() {
        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue(object : Callback<MoviesResponse>{
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