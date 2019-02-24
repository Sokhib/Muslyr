package com.databind.aquaholic.muslyr.data.db.entity

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("track_list")
    val trackList: List<Track>
)