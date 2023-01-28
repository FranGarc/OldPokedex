package com.garciafrancisco.data.source

import com.garciafrancisco.data.PokedexListEntry

interface PokedexRepository {
    fun getEntries(amountOfEntries: Int, startFromPokemonId: Int): List<PokedexListEntry>
}