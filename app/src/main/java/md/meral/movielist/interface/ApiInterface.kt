package md.meral.movielist.`interface`

import md.meral.movielist.model.Language
import md.meral.movielist.model.MovieDetails
import md.meral.movielist.util.Constants.API_KEY
import md.meral.movielist.util.Constants.BASE_URL
import md.meral.movielist.model.MoviesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    var language: String

    @GET("movie/popular?api_key=$API_KEY&page=10")
    fun getMovies(@Query("language") language: String): Call<MoviesResponse>

    @GET("movie/{movieId}?api_key=$API_KEY")
    fun getMovieDetails(@Path("movieId") movieId: Int, @Query("language") language: String): Call<MovieDetails>

    @GET("configuration/languages?api_key=$API_KEY")
    fun getLanguages(): Call<List<Language>>

    @GET("search/movie?api_key=$API_KEY")
    fun getMoviesBySearch(@Query("query") query: String): Call<MoviesResponse>

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