package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Other(
    @Json(name = "dream_world") val dream_world: DreamWorld,
    @Json(name = "home") val home: Home,
    @Json(name = "official-artwork")val officialArtwork: OfficialArtwork
)