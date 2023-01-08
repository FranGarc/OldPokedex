package com.garciafrancisco.pokedex.data.remote.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
//    @Json(name = "abilities") val abilities: List<Ability>,
    @Json(name = "base_experience") val base_experience: Int,
//    @Json(name = "forms") val forms: List<Form>,
//    @Json(name = "game_indices") val game_indices: List<GameIndice>,
    @Json(name = "height") val height: Int,
//    @Json(name = "held_items") val held_items: List<Item>,
    @Json(name = "id") val id: Int,
    @Json(name = "is_default") val is_default: Boolean,
//    @Json(name = "location_area_encounters") val location_area_encounters: String,
//    @Json(name = "moves") val moves: List<Move>,
    @Json(name = "name") val name: String,
    @Json(name = "order") val order: Int,
//    @Json(name = "past_types") val past_types: List<Any>,
//    @Json(name = "species") val species: Species,
    @Json(name = "sprites") val sprites: Sprites,
    @Json(name = "stats") val stats: List<Stat>,
    @Json(name = "types") val types: List<Type>,
    @Json(name = "weight") val weight: Int
)