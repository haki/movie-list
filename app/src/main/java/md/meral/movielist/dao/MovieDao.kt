package md.meral.movielist.dao

import androidx.room.*
import md.meral.movielist.model.Movie

@Dao
interface MovieDao {

    @Insert // Create
    fun insert(movie: Movie)

    @Query("SELECT * FROM movies") // Read
    fun getAll(): List<Movie>

    @Update // Update
    fun update(movie: Movie)

    @Delete // Delete
    fun delete(movie: Movie)
}