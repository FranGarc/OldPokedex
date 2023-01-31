package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Language(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)