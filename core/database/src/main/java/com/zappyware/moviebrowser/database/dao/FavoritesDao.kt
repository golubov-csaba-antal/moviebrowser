package com.zappyware.moviebrowser.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zappyware.moviebrowser.database.entity.MBConstants
import com.zappyware.moviebrowser.database.entity.MBFavoriteMovie

@Dao
interface FavoritesDao {

    @Query("SELECT COUNT(*) FROM ${MBConstants.Tables.T_FAVORITE_MOVIES} WHERE ${MBConstants.Columns.C_MOVIE_ID} = :movieId")
    fun isFavorites(movieId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(movie: MBFavoriteMovie)

    @Delete
    fun removeFromFavorites(movie: MBFavoriteMovie)
}
