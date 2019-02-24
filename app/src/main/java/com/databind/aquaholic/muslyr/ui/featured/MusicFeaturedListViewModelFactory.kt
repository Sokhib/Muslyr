package com.databind.aquaholic.muslyr.ui.featured

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.databind.aquaholic.muslyr.data.repository.TracksRepository

class MusicFeaturedListViewModelFactory(private val tracksRepository: TracksRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MusicFeaturedListViewModel(tracksRepository) as T
    }
}