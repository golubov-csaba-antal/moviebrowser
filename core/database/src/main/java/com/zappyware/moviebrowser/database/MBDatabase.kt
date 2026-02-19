package com.zappyware.moviebrowser.database

import com.zappyware.moviebrowser.database.dao.FavoritesDao
import com.zappyware.moviebrowser.database.dao.MoviesDao

interface MBDatabase {
    fun moviesDao(): MoviesDao
    fun favoritesDao(): FavoritesDao
}
