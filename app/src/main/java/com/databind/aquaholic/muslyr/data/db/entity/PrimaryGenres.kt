package com.databind.aquaholic.muslyr.data.db.entity

import com.google.gson.annotations.SerializedName

data class PrimaryGenres(
    @SerializedName("music_genre_list")
    val musicGenreList: List<MusicGenre>
)