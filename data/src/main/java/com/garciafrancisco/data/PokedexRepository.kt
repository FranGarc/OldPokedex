package com.garciafrancisco.data

interface PokedexRepository {
    suspend fun getPokedexEntries(amountOfEntries: Int, startFromPokemonId: Int): List<DataPokedexListEntry>
}