package md.meral.movielist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import md.meral.movielist.R
import md.meral.movielist.`interface`.ApiInterface
import md.meral.movielist.adapter.MovieRecyclerAdapter
import md.meral.movielist.model.Language
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}