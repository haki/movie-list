package md.meral.movielist.util

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverters
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type

        return Gson().fromJson(value, listType)
    }

    @TypeConverters
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}