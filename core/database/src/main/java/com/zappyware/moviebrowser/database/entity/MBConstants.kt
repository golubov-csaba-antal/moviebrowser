package com.zappyware.moviebrowser.database.entity

object MBConstants {

    const val DB_NAME = "hotstar.db"

    object Tables {
        const val T_MOVIES = "t_movies"
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
    }

}