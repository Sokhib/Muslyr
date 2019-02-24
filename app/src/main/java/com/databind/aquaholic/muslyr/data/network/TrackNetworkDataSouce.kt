package com.databind.aquaholic.muslyr.data.network

import androidx.lifecycle.LiveData
import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse
import com.databind.aquaholic.muslyr.data.network.response.TopResponse
import com.databind.aquaholic.muslyr.data.network.response.TrackLyricsResponse

//Is it okay to write hard-coded values here??, must be provided as constant in implementation

interface TrackNetworkDataSource {
    val downloadedTracks: LiveData<TopResponse>
    val downloadedLyrics: LiveData<TrackLyricsResponse>
    suspend fun fetchTracks(
        chartName: String = "top",
        page: Int = 1,
        pageSize: Int = 10,
        country: String = "tr",
        hasLyrics: Int = 1
    )

    suspend fun fetchSearchTracks(
        searchQuery: String,
        page: Int = 1,
        pageSize: Int = 10,
        hasLyrics: Int = 1,
        trackRating: String = "desc"
    ): SearchTrackResponse

    suspend fun fetchTrackLyrics(trackId: Int, trackName: String, trackArtist: String)

}