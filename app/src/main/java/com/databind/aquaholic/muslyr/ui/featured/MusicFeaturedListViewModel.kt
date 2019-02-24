package com.databind.aquaholic.muslyr.ui.featured

import androidx.lifecycle.ViewModel
import com.databind.aquaholic.muslyr.data.repository.TracksRepository
import com.databind.aquaholic.muslyr.internal.lazyDeferred

class MusicFeaturedListViewModel(
    private val tracksRepository: TracksRepository
) : ViewModel() {
    val tracks by lazyDeferred {
        tracksRepository.getTopTracks()
    }
}
