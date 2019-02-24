package com.databind.aquaholic.muslyr.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse
import com.databind.aquaholic.muslyr.data.repository.TracksRepository

class MusicSearchListViewModel(private val tracksRepository: TracksRepository) :
    ViewModel() {

    val querySearch = MutableLiveData<String>()

    fun search(query: String = "") {
        querySearch.postValue(query)
    }

    suspend fun searchForTracks(query: String): SearchTrackResponse {
        return tracksRepository.getSearchedTracksList(query)
    }

    /*    val searchedTracks by lazyDeferred {
        //CoroutineScope
        // Pass the query
        tracksRepository.getSearchedTracksList("Eminem")
    }*/
}