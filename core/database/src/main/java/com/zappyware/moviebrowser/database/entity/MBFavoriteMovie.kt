package com.zappyware.moviebrowser.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(MBConstants.Tables.T_FAVORITE_MOVIES)
data class MBFavoriteMovie(
    @PrimaryKey
    @ColumnInfo(MBConstants.Columns.C_MOVIE_ID)
    val movieId: Long,
)

fun Long.toMBFavoriteMovie() = MBFavoriteMovie(this)
