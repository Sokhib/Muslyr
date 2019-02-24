package com.databind.aquaholic.muslyr.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val TRACK_ID = 0

@Entity(tableName = "tracks", indices = [Index(value = ["trackId"], unique = true)])
data class TrackX(
    @SerializedName("album_id")
    val albumId: Int,
    @SerializedName("album_name")
    val albumName: String,
    @SerializedName("artist_id")
    val artistId: Int,
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("commontrack_id")
    val commontrackId: Int,
    val explicit: Int,
    @SerializedName("has_lyrics")
    val hasLyrics: Int,
    @SerializedName("has_richsync")
    val hasRichsync: Int,
    @SerializedName("num_favourite")
    val numFavourite: Int,
    val restricted: Int,
    @SerializedName("track_edit_url")
    val trackEditUrl: String,
    @SerializedName("track_id")
    val trackId: Int,
    @SerializedName("track_name")
    val trackName: String,
    @SerializedName("track_rating")
    val trackRating: Int,
    @SerializedName("track_share_url")
    val trackShareUrl: String,
    @SerializedName("updated_time")
    val updatedTime: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = TRACK_ID

}