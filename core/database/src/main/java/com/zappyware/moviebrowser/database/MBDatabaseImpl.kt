package com.zappyware.moviebrowser.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zappyware.moviebrowser.database.entity.MBFavoriteMovie

@Database(
    entities = [
        MBFavoriteMovie::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class MBDatabaseImpl : RoomDatabase(), MBDatabase
