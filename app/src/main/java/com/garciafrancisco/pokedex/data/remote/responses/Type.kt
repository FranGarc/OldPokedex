package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Type(
    @Json(name = "slot") val slot: Int,
    @Json(name = "type") val type: TypeX
)