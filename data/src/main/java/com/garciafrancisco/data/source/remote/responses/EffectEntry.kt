package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EffectEntry(
    @Json(name = "effect") val effect: String,
    @Json(name = "language") val language: Language,
    @Json(name = "short_effect") val short_effect: String
)