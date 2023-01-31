package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameIndice(
    @Json(name = "game_index") val game_index: Int,
    @Json(name = "version") val version: Version
)