package com.databind.aquaholic.muslyr.data.db.entity

import com.google.gson.annotations.SerializedName

data class MusicGenre(
    @SerializedName("music_genre")
    val musicGenre: MusicGenreX
)