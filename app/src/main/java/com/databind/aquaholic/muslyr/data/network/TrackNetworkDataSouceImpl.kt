//cleared search LiveData objs
package com.databind.aquaholic.muslyr.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse
import com.databind.aquaholic.muslyr.data.network.response.TopResponse
import com.databind.aquaholic.muslyr.data.network.response.TrackLyricsResponse
import com.databind.aquaholic.muslyr.internal.NoConnectivityException

class TrackNetworkDataSourceImpl(private val musixAPIService: MusixAPIService) : TrackNetworkDataSource {
    private val _downloadedTracks = MutableLiveData<TopResponse>()
    override val downloadedTracks: LiveData<TopResponse>
        get() = _downloadedTracks

    override suspend fun fetchTracks(chartName: String, page: Int, pageSize: Int, country: String, hasLyrics: Int) {
        try {
            val fetchedTracks = musixAPIService.getTopTracks(chartName, page, pageSize, country, hasLyrics)
                .await()
            _downloadedTracks.postValue(fetchedTracks)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection")
        }
    }

    override suspend fun fetchSearchTracks(
        searchQuery: String,
        page: Int,
        pageSize: Int,
        hasLyrics: Int,
        trackRating: String
    ): SearchTrackResponse {
        return musixAPIService.getSearchResults(searchQuery, page, pageSize, hasLyrics, trackRating).await()
    }

    private val _downloadedLyrics = MutableLiveData<TrackLyricsResponse>()
    override val downloadedLyrics: LiveData<TrackLyricsResponse>
        get() = _downloadedLyrics

    override suspend fun fetchTrackLyrics(trackId: Int, trackName: String, trackArtist: String) {
        try {
            val fetchedLyrics = musixAPIService.getTrackLyrics(trackId)
                .await()
            fetchedLyrics.message.body.lyrics.trackId = trackId
            fetchedLyrics.message.body.lyrics.trackName = trackName
            fetchedLyrics.message.body.lyrics.trackArtist = trackArtist
            _downloadedLyrics.postValue(fetchedLyrics)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection")
        }
    }

}