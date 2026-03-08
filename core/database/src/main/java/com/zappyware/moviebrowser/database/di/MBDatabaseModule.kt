package com.zappyware.moviebrowser.database.di

import android.content.Context
import androidx.room.Room
import com.zappyware.moviebrowser.database.MBDatabase
import com.zappyware.moviebrowser.database.MBDatabaseImpl
import com.zappyware.moviebrowser.database.entity.MBConstants
import com.zappyware.moviebrowser.database.migration.MIGRATE_1_2
import com.zappyware.moviebrowser.database.migration.MIGRATE_2_3
import com.zappyware.moviebrowser.database.migration.MIGRATE_3_4
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MBDatabaseModule {
    @Reusable
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MBDatabase {
        return Room.databaseBuilder(context, MBDatabaseImpl::class.java, MBConstants.DB_NAME)
            .addMigrations(MIGRATE_1_2)
            .addMigrations(MIGRATE_2_3)
            .addMigrations(MIGRATE_3_4)
            .fallbackToDestructiveMigration(true)
            .build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {
    @Reusable
    @Provides
    fun provideFavoritesDao(db: MBDatabase) = db.favoritesDao()
}
