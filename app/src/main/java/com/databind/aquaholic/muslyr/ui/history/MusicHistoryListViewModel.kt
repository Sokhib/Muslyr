package com.databind.aquaholic.muslyr.ui.history

import androidx.lifecycle.ViewModel
import com.databind.aquaholic.muslyr.data.repository.TracksRepository

class MusicHistoryListViewModel(tracksRepository: TracksRepository) : ViewModel() {
    val viewedTracks = tracksRepository.getTrackLyricsHistoryList()
}
