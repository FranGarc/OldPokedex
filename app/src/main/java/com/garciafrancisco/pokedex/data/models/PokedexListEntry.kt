package com.garciafrancisco.pokedex.data.models

import com.garciafrancisco.pokedex.data.remote.responses.Result
import com.garciafrancisco.pokedex.util.extensions.pokeCapitalize

data class PokedexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val pokeApiId: Int
)

fun List<Result>.toPokedexListOfEntries(): List<PokedexListEntry> {
    return this.mapIndexed { index, entry ->
        val pokeapiID = extractIdFromUrl(entry.url)
        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeapiID}.png"
        PokedexListEntry(entry.name.pokeCapitalize(), imageUrl, pokeapiID.toInt())
    }
}

private fun extractIdFromUrl(url: String): String {
    return if (url.endsWith("/")) {
        url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        url.takeLastWhile { it.isDigit() }
    }
}