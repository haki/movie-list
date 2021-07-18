package md.meral.movielist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(
    @ColumnInfo(name = "genre_ids") @SerializedName("genre_ids") val genreIds: List<Int>?,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "original_language") @SerializedName("original_language") val originalLanguage: String?,
    @ColumnInfo(name = "original_title") @SerializedName("original_title") val originalTitle: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String?,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseDate: String?,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String?,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double?,
    @ColumnInfo(name = "vote_count") @SerializedName("vote_count") val voteCount: Int?
)
