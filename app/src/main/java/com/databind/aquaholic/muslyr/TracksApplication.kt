package com.databind.aquaholic.muslyr

import android.app.Application
import com.databind.aquaholic.muslyr.data.db.TrackDatabase
import com.databind.aquaholic.muslyr.data.network.*
import com.databind.aquaholic.muslyr.data.repository.TracksRepository
import com.databind.aquaholic.muslyr.data.repository.TracksRepositoryImpl
import com.databind.aquaholic.muslyr.ui.details.LyricsViewModelFactory
import com.databind.aquaholic.muslyr.ui.featured.MusicFeaturedListViewModelFactory
import com.databind.aquaholic.muslyr.ui.history.MusicHistoryListViewModelFactory
import com.databind.aquaholic.muslyr.ui.search.MusicSearchListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TracksApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TracksApplication))
        bind() from singleton { TrackDatabase(instance()) }
        bind() from singleton { instance<TrackDatabase>().trackDao() }
        bind() from singleton { instance<TrackDatabase>().lyricsDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { MusixAPIService(instance()) }
        bind<TrackNetworkDataSource>() with singleton { TrackNetworkDataSourceImpl(instance()) }
        bind<TracksRepository>() with singleton { TracksRepositoryImpl(instance(), instance(), instance()) }
        bind() from provider { MusicFeaturedListViewModelFactory(instance()) }
        bind() from provider { MusicSearchListViewModelFactory(instance()) }
        bind() from provider { LyricsViewModelFactory(instance()) }
        bind() from provider { MusicHistoryListViewModelFactory(instance()) }


    }
}