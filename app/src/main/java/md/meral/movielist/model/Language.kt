package md.meral.movielist.model

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("iso_639_1") val iso6391: String,
    @SerializedName("english_name") val englishName: String,
    val name: String,
)
