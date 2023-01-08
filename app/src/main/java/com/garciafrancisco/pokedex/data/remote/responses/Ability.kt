package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "ability") val ability: AbilityX,
    @Json(name = "is_hidden") val is_hidden: Boolean,
    @Json(name = "slot") val slot: Int
)