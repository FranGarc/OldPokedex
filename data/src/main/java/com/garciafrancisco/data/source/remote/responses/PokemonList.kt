package com.garciafrancisco.data.source.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class PokemonList(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String?, //url "https://pokeapi.co/api/v2/pokemon/?offset=40&limit=20"
    @Json(name = "previous") val previous: String?, //url "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=20"
    @Json(name = "results") val results: List<Result>
)