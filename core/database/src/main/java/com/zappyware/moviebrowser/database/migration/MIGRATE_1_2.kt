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
