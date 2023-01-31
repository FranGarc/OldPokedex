package com.garciafrancisco.domain.model

import com.garciafrancisco.data.DataPokedexListEntry

data class PokedexEntry(
    val pokemonName: String,
    val url: String,
    val pokeApiId: Int,
    val imageUrl: String
)


fun DataPokedexListEntry.toPokedexListEntry() = PokedexEntry(
    pokemonName = this.pokemonName,
    url = this.url,
    pokeApiId = extractPokeId(this.url),
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${extractPokeId(this.url)}.png"
)

fun extractPokeId(url: String): Int {
    return if (url.endsWith("/")) {
        url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        url.takeLastWhile { it.isDigit() }
    }.toInt()

}
