package com.zappyware.moviebrowser.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zappyware.moviebrowser.database.entity.MBFavoriteMovie
import com.zappyware.moviebrowser.database.entity.MBMovie

@Database(
    entities = [
        MBMovie::class,
        MBFavoriteMovie::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class MBDatabaseImpl : RoomDatabase(), MBDatabase
