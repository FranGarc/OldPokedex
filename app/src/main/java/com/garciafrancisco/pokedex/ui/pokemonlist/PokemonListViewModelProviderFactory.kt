package com.garciafrancisco.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garciafrancisco.pokedex.repository.PokedexRepository

class PokemonListViewModelProviderFactory(
    private val pokedexRepository: PokedexRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonListViewModel(pokedexRepository) as T
    }
}