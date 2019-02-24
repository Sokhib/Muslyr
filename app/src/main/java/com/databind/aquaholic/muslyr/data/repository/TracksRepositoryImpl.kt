package com.databind.aquaholic.muslyr.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.databind.aquaholic.muslyr.data.db.LyricsDAO
import com.databind.aquaholic.muslyr.data.db.TrackDAO
import com.databind.aquaholic.muslyr.data.db.entity.Lyrics
import com.databind.aquaholic.muslyr.data.db.entity.TrackX
import com.databind.aquaholic.muslyr.data.network.TrackNetworkDataSource
import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse
import com.databind.aquaholic.muslyr.data.network.response.TopResponse
import com.databind.aquaholic.muslyr.data.network.response.TrackLyricsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TracksRepositoryImpl(
    private val trackDAO: TrackDAO,
    private val lyricsDAO: LyricsDAO,
    private val trackNetworkDataSource: TrackNetworkDataSource
) : TracksRepository {
    override fun getTrackLyricsHistoryList(): LiveData<List<Lyrics>> {
        return lyricsDAO.getTrackLyricsHistoryList()
    }

    init {
        trackNetworkDataSource.downloadedTracks.observeForever { topResponse ->
            persistFetchedTracks(topResponse)
        }
        trackNetworkDataSource.downloadedLyrics.observeForever { fetchedLyrics ->
            persistFetchedLyrics(fetchedLyrics)
        }
    }

    override suspend fun getTrackLyrics(
        id: Int,
        trackName: String,
        trackArtist: String
    ): LiveData<Lyrics> {
        return withContext(Dispatchers.IO) {
            initLyricsData(id, trackName, trackArtist)
            return@withContext lyricsDAO.getTrackLyrics(id)
        }
    }

    private suspend fun initLyricsData(id: Int, trackName: String, trackArtist: String) {
        trackNetworkDataSource.fetchTrackLyrics(id, trackName, trackArtist)
    }

    private fun persistFetchedLyrics(fetchedLyrics: TrackLyricsResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            if (fetchedLyrics != null) {
                lyricsDAO.insert(fetchedLyrics.message.body.lyrics)
            }
        }
    }


    override suspend fun getTopTracks(): LiveData<List<TrackX>> {
        return withContext(Dispatchers.IO) {
            initTrackData()
            return@withContext trackDAO.getTopTracks()
        }
    }

    override suspend fun getSearchedTracksList(searchQuery: String): SearchTrackResponse {
        return trackNetworkDataSource.fetchSearchTracks(searchQuery)
    }

    private fun persistFetchedTracks(fetchedTracks: TopResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            for (i in fetchedTracks.message.body.trackList) {
                Log.d("MyLog", i.track.trackName)
                trackDAO.upsert(i.track)
            }
        }
    }

    private suspend fun initTrackData() {
        trackNetworkDataSource.fetchTracks()
    }

}