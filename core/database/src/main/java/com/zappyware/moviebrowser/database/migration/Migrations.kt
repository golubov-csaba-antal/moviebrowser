package com.zappyware.moviebrowser.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zappyware.moviebrowser.database.entity.MBConstants

val MIGRATE_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS ${MBConstants.Tables.T_FAVORITE_MOVIES} (" +
                    "${MBConstants.Columns.C_MOVIE_ID} LONG, " +
                    "PRIMARY KEY(${MBConstants.Columns.C_MOVIE_ID}))",
        )
    }
}

val MIGRATE_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE t_movies")
    }
}

val MIGRATE_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        val favoritesTable = "old_favorites"
        db.execSQL("ALTER TABLE ${MBConstants.Tables.T_FAVORITE_MOVIES} RENAME TO $favoritesTable")
        db.execSQL(
            "CREATE TABLE ${MBConstants.Tables.T_FAVORITE_MOVIES} (" +
                    "${MBConstants.Columns.C_MOVIE_ID} TEXT NOT NULL, " +
                    "PRIMARY KEY(${MBConstants.Columns.C_MOVIE_ID}))",
        )
        db.execSQL("INSERT INTO ${MBConstants.Tables.T_FAVORITE_MOVIES} SELECT * FROM $favoritesTable")
        db.execSQL("DROP TABLE $favoritesTable")
    }
}
