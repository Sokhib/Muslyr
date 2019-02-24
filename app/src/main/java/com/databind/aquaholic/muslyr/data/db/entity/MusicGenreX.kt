package com.databind.aquaholic.muslyr.data.db.entity

import com.google.gson.annotations.SerializedName

data class MusicGenreX(
    @SerializedName("music_genre_id")
    val musicGenreId: Int,
    @SerializedName("music_genre_name")
    val musicGenreName: String,
    @SerializedName("music_genre_name_extended")
    val musicGenreNameExtended: String,
    @SerializedName("music_genre_parent_id")
    val musicGenreParentId: Int,
    @SerializedName("music_genre_vanity")
    val musicGenreVanity: String
)