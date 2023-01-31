package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @Json(name = "x-y") val xy: XY
)