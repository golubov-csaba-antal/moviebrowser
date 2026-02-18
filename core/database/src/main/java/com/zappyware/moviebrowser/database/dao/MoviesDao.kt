package com.zappyware.moviebrowser.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zappyware.moviebrowser.database.entity.MBConstants
import com.zappyware.moviebrowser.database.entity.MBMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM ${MBConstants.Tables.T_MOVIES}")
    fun getMovies(): Flow<MBMovie>

    @Query("SELECT * FROM ${MBConstants.Tables.T_MOVIES} WHERE ${MBConstants.Columns.C_ID} = :id")
    fun getMovieByContentId(id: Long): MBMovie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MBMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MBMovie)

    @Query("DELETE FROM ${MBConstants.Tables.T_MOVIES}")
    fun clearMovies()
}
