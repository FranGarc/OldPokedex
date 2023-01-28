package com.garciafrancisco.data

data class PokedexListEntry(
    val pokemonName: String,
    val url: String,

    ) {
    val pokeApiId: Int
        get() = if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }.toInt()
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${this.pokeApiId}.png"
}
