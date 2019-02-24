package com.databind.aquaholic.muslyr.data.network

import com.databind.aquaholic.muslyr.data.network.response.SearchTrackResponse
import com.databind.aquaholic.muslyr.data.network.response.TopResponse
import com.databind.aquaholic.muslyr.data.network.response.TrackLyricsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "94574451eaaff749615db524718f75c6"

interface MusixAPIService {
    //top
    //http://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=top&page=1&page_size=2&country=tr&f_has_lyrics=1&apikey=94574451eaaff749615db524718f75c6
    @GET("chart.tracks.get")
    fun getTopTracks(
        @Query("chart_name") chartName: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("country") country: String,
        @Query("f_has_lyrics") hasLyrics: Int
    ): Deferred<TopResponse>

    //search
    //http://api.musixmatch.com/ws/1.1/track.search?q=lose yourself&page_size=2&page=1&s_track_rating=desc&apikey=94574451eaaff749615db524718f75c6
    @GET("track.search")
    fun getSearchResults(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("f_has_lyrics") hasLyrics: Int,
        @Query("s_track_rating") trackRating: String
    ): Deferred<SearchTrackResponse>

    //track lyrics
    //http://api.musixmatch.com/ws/1.1/track.lyrics.get?track_id=1809819&apikey=94574451eaaff749615db524718f75c6
    @GET("track.lyrics.get")
    fun getTrackLyrics(
        @Query("track_id") trackId: Int
    ): Deferred<TrackLyricsResponse>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): MusixAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.musixmatch.com/ws/1.1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusixAPIService::class.java)
        }
    }
}