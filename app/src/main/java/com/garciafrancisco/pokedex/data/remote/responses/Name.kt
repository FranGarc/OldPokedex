package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Name(
    @Json(name = "language") val language: Language,
    @Json(name = "name") val name: String
)