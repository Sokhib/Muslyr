package com.databind.aquaholic.muslyr.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val TRACK_LYRICS_ID = 0
const val TRACK_LYRICS_NAME = "Hello"
const val TRACK_LYRICS_ARTIST = "Hello"

@Entity(tableName = "track_lyrics", indices = [Index(value = ["lyricsId"], unique = true)])
data class Lyrics(
    val explicit: Int,
    @SerializedName("lyrics_body")
    val lyricsBody: String,
    @SerializedName("lyrics_copyright")
    val lyricsCopyright: String,
    @SerializedName("lyrics_id")
    val lyricsId: Int,
    @SerializedName("pixel_tracking_url")
    val pixelTrackingUrl: String,
    @SerializedName("script_tracking_url")
    val scriptTrackingUrl: String,
    @SerializedName("updated_time")
    val updatedTime: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = TRACK_LYRICS_ID
    @SerializedName("track_name")
    var trackName: String = TRACK_LYRICS_NAME
    @SerializedName("track_id")
    var trackId: Int = TRACK_ID
    @SerializedName("track_artist")
    var trackArtist: String = TRACK_LYRICS_ARTIST

}