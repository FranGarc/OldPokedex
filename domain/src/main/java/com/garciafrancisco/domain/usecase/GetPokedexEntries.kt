package com.garciafrancisco.domain.usecase

import com.garciafrancisco.data.DataPokedexRepository
import com.garciafrancisco.data.PokedexRepository
import com.garciafrancisco.domain.model.PokedexEntry

class GetPokedexEntries(private val repository: PokedexRepository = DataPokedexRepository()) {
    suspend operator fun invoke(amount: Int, startId: Int): List<PokedexEntry> {
        return repository.getPokedexEntries(amountOfEntries = amount, startFromPokemonId = startId)
    }
}