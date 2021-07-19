package md.meral.movielist.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanies>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
)

data class Genres(
    val id: Int,
    val name: String,
)

data class ProductionCompanies(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String,
    @SerializedName("origin_country") val originCountry: String,
)