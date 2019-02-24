package com.databind.aquaholic.muslyr.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics
import com.databind.aquaholic.muslyr.data.db.entity.TrackX

@Database(
    entities = [TrackX::class, Lyrics::class],
    version = 1,
    exportSchema = false
)

abstract class TrackDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDAO
    abstract fun lyricsDao(): LyricsDAO

    companion object {
        @Volatile
        private var INSTANCE: TrackDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TrackDatabase::class.java, "track.db"
        ).build()
    }
}