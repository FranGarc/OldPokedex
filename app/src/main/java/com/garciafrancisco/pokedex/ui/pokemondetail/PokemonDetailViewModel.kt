package com.garciafrancisco.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garciafrancisco.pokedex.data.models.PokedexListEntry
import com.garciafrancisco.pokedex.data.models.toPokemonData
import com.garciafrancisco.pokedex.data.remote.responses.Pokemon
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.util.Constants
import com.garciafrancisco.pokedex.util.Resource
import com.garciafrancisco.pokedex.util.extensions.pokeCapitalize
import kotlinx.coroutines.launch

private const val TAG = "PokemonDetailViewModel"

class PokemonDetailViewModel(private val repository: PokedexRepository = PokedexRepository()) :
    ViewModel() {

    private val _pokemonData: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemonData: LiveData<Pokemon> = _pokemonData
    private val _loadError: MutableLiveData<String> = MutableLiveData()
    val loadError: LiveData<String> = _loadError
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadPokemonData(pokemonId: String) {
        Log.d(TAG, "pokemonId( $pokemonId )")

        viewModelScope.launch {
            _isLoading.postValue(true)
            val result =
                repository.getPokemonInfoById(pokemonId)
            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "loadPokemonData() Success")
                    val pokemon = result.data!!//?.toPokemonData()
                    _pokemonData.postValue(pokemon)
                    _loadError.postValue("")
                    _isLoading.postValue(false)

                }
                is Resource.Error -> {
                    _loadError.postValue(result.message!!)
                    _isLoading.postValue(false)
                }
                is Resource.Loading -> {
                    _isLoading.postValue(true)
                }
            }
        }
    }
}