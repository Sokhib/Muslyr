package com.databind.aquaholic.muslyr.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.databind.aquaholic.muslyr.data.db.entity.TrackX

@Dao
interface TrackDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(track: TrackX)

    @Query("select * from tracks")
    fun getTopTracks(): LiveData<List<TrackX>>
}