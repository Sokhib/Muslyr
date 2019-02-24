package com.databind.aquaholic.muslyr.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.databind.aquaholic.muslyr.data.repository.TracksRepository

class MusicHistoryListViewModelFactory(private val tracksRepository: TracksRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MusicHistoryListViewModel(tracksRepository) as T
    }
}