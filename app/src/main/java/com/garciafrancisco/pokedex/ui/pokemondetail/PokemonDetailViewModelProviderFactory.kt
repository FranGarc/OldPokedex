package com.garciafrancisco.pokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garciafrancisco.pokedex.repository.PokedexRepository

class PokemonDetailViewModelProviderFactory(
    val pokedexRepository: PokedexRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonDetailViewModel(pokedexRepository) as T
    }

}