package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Yellow(
    @Json(name = "back_default") val back_default: String,
    @Json(name = "back_gray") val back_gray: String,
    @Json(name = "back_transparent") val back_transparent: String,
    @Json(name = "front_default") val front_default: String,
    @Json(name = "front_gray") val front_gray: String,
    @Json(name = "front_transparent") val front_transparent: String
)