package com.zappyware.moviebrowser.database.entity

object MBConstants {

    const val DB_NAME = "moviebrowser.db"

    object Tables {
        const val T_MOVIES = "t_movies"
        const val T_FAVORITE_MOVIES = "t_favorite_movies"
    }

    object Columns {
        const val C_ID = "c_id"
        const val C_TITLE = "c_title"
        const val C_GENRES = "c_genres"
        const val C_OVERVIEW = "c_overview"
        const val C_SMALL_COVER_URL = "c_small_cover_url"
        const val C_COVER_URL = "c_cover_url"
        const val C_RATING = "c_rating"
        const val C_IS_FAVORITE = "c_is_favorite"
        const val C_MOVIE_ID = "c_movie_id"
    }
}
