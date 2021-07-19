package md.meral.movielist.`interface`

import md.meral.movielist.model.Language
import md.meral.movielist.model.MovieDetails
import md.meral.movielist.util.Constants.API_KEY
import md.meral.movielist.util.Constants.BASE_URL
import md.meral.movielist.model.MoviesResponse
import md.meral.movielist.util.Constants.LANGUAGE_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    var language: String

    @GET("popular?api_key=$API_KEY&page=10")
    fun getMovies(@Query("language") language: String): Call<MoviesResponse>

    @GET("{movieId}?api_key=$API_KEY")
    fun getMovieDetails(@Path("movieId") movieId: Int, @Query("language") language: String): Call<MovieDetails>

    @GET("languages?api_key=$API_KEY")
    fun getLanguages(): Call<List<Language>>

    companion object {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

        fun createLanguage(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(LANGUAGE_BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}