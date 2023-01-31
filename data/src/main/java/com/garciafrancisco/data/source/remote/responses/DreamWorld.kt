package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class DreamWorld(
    @Json(name = "front_default") val front_default: String,
    @Json(name = "front_female") val front_female: Any
)