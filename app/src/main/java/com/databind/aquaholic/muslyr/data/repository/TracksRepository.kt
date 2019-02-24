package com.databind.aquaholic.muslyr.data.repository

import androidx.lifecycle.LiveData
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics
import com.databind.aquaholic.muslyr.data.db.entity.TrackX
import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse

interface TracksRepository {
    suspend fun getTopTracks(): LiveData<List<TrackX>>
    suspend fun getSearchedTracksList(searchQuery: String = ""): SearchTrackResponse
    suspend fun getTrackLyrics(id: Int, trackName: String, trackArtist: String): LiveData<Lyrics>
    fun getTrackLyricsHistoryList(): LiveData<List<Lyrics>>
}