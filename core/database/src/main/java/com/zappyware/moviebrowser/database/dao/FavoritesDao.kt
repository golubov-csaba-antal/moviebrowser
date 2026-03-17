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
    suspend fun isFavorites(movieId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(movie: MBFavoriteMovie)

    @Delete
    suspend fun removeFromFavorites(movie: MBFavoriteMovie)
}
