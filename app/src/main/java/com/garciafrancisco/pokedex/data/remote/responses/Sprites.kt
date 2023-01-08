package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Sprites(
    @Json(name = "back_default") val back_default: String?,
    @Json(name = "back_female") val back_female: String?,
    @Json(name = "back_shiny") val back_shiny: String?,
    @Json(name = "back_shiny_female") val back_shiny_female: String?,
    @Json(name = "front_default") val front_default: String,
    @Json(name = "front_female") val front_female: String?,
    @Json(name = "front_shiny") val front_shiny: String?,
    @Json(name = "front_shiny_female") val front_shiny_female: String?,
//    @Json(name = "other") val other: String?,
//    @Json(name = "versions") val versions: Versions?
)