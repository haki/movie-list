package md.meral.movielist.`interface`

import md.meral.movielist.model.MovieDetails
import md.meral.movielist.util.Constants.API_KEY
import md.meral.movielist.util.Constants.BASE_URL
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("popular?api_key=$API_KEY&language=us&page=10")
    fun getMovies(): Call<MoviesResponse>

    @GET("{movieId}?api_key=$API_KEY&language=us}")
    fun getMovieDetails(@Path("movieId") movieId: Int): Call<MovieDetails>

    companion object {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}