package com.zappyware.moviebrowser.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zappyware.moviebrowser.data.Movie
import javax.annotation.concurrent.Immutable

@Entity(tableName = MBConstants.Tables.T_MOVIES)
@Immutable
data class MBMovie(
    @PrimaryKey
    @ColumnInfo(name = MBConstants.Columns.C_ID)
    val id: Long,
    @ColumnInfo(name = MBConstants.Columns.C_TITLE)
    val title: String,
    @ColumnInfo(name = MBConstants.Columns.C_GENRES)
    var genres: String,
    @ColumnInfo(name = MBConstants.Columns.C_OVERVIEW)
    val overview: String?,
    @ColumnInfo(name = MBConstants.Columns.C_SMALL_COVER_URL)
    val smallCoverUrl: String?,
    @ColumnInfo(name = MBConstants.Columns.C_COVER_URL)
    val coverUrl: String?,
    @ColumnInfo(name = MBConstants.Columns.C_RATING)
    val rating: Float,
    @ColumnInfo(name = MBConstants.Columns.C_IS_FAVORITE)
    var isFavorite: Boolean,
)

fun MBMovie.toMovie(): Movie {
    return Movie(id, title, genres, overview, smallCoverUrl, coverUrl, rating, isFavorite)
}

fun Movie.toMBMovie(): MBMovie {
    return MBMovie(id, title, genres, overview, smallCoverUrl, coverUrl, rating, isFavorite)
}
