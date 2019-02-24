package com.databind.aquaholic.muslyr.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics

@Dao
interface LyricsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lyrics: Lyrics)


    @Query("select * from track_lyrics order by id desc")
    fun getTrackLyricsHistoryList(): LiveData<List<Lyrics>>

    @Query("select * from track_lyrics where trackId=:trackId")
    fun getTrackLyrics(trackId: Int): LiveData<Lyrics>
}