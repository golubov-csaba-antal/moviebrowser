package com.zappyware.moviebrowser.database

import com.zappyware.moviebrowser.database.dao.FavoritesDao

interface MBDatabase {
    fun favoritesDao(): FavoritesDao
}
