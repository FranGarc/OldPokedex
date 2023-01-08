package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class GenerationI(
    @Json(name = "red-blue-i") val redBlue: RedBlue,
    @Json(name = "yellow") val yellow: Yellow
)