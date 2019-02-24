package com.databind.aquaholic.muslyr.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics
import com.databind.aquaholic.muslyr.data.repository.TracksRepository

class LyricsViewModel(private val tracksRepository: TracksRepository) : ViewModel() {
    suspend fun getLyrics(id: Int, trackName: String, trackArtist: String): LiveData<Lyrics> {
        return tracksRepository.getTrackLyrics(id, trackName, trackArtist)
    }
}
