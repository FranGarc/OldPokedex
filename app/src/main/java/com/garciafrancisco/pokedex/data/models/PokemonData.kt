package com.garciafrancisco.pokedex.data.models

import com.garciafrancisco.pokedex.data.remote.responses.Pokemon

data class PokemonData(
    val pokemonName: String,
    val pokeApiId: Int,
    val height: Int,
    val weight: Int,
    val types: List<String>,
)


fun Pokemon.toPokemonData(): PokemonData {
    val types = this.types.mapIndexed { index, type ->
        type.type.name
    }
    return PokemonData(
        pokemonName = this.name,
        pokeApiId = this.id,
        height = this.height,
        weight = this.weight,
        types = types
    )
}