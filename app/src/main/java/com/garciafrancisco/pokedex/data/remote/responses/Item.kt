package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "attributes") val attributes: List<Attribute>,
    @Json(name = "baby_trigger_for") val baby_trigger_for: Any,
    @Json(name = "category") val category: Category,
    @Json(name = "cost") val cost: Int,
    @Json(name = "effect_entries") val effect_entries: List<EffectEntry>,
    @Json(name = "flavor_text_entries") val flavor_text_entries: List<FlavorTextEntry>,
    @Json(name = "fling_effect") val fling_effect: Any,
    @Json(name = "fling_power") val fling_power: Any,
    @Json(name = "game_indices") val game_indices: List<GameIndiceX>,
    @Json(name = "held_by_pokemon") val held_by_pokemon: List<Any>,
    @Json(name = "id") val id: Int,
    @Json(name = "machines") val machines: List<Any>,
    @Json(name = "name") val name: String,
    @Json(name = "names") val names: List<Name>,
    @Json(name = "sprites") val sprites: SpritesX
)