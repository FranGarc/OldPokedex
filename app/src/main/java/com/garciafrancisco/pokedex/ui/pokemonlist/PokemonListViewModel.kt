package com.garciafrancisco.pokedex.ui.pokemonlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garciafrancisco.pokedex.data.models.PokedexListEntry
import com.garciafrancisco.pokedex.data.models.toPokedexListOfEntries
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.util.Constants.PAGE_SIZE
import com.garciafrancisco.pokedex.util.Resource
import kotlinx.coroutines.launch

private const val TAG = "PokemonListViewModel"

class PokemonListViewModel(private val repository: PokedexRepository = PokedexRepository()) :
    ViewModel() {


    private val _pokemonList: MutableLiveData<List<PokedexListEntry>> = MutableLiveData()
    val pokemonList: LiveData<List<PokedexListEntry>> = _pokemonList
    private var currentPage = 0

    private val _loadError: MutableLiveData<String> = MutableLiveData()
    val loadError: LiveData<String> = _loadError
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private var cachedPokemonList = listOf<PokedexListEntry>()

    private var totalResults: Int = -1

    init {
        Log.d(TAG, "init()")

        loadPokemonPaginated()
    }


    fun totalResults() = totalResults
    fun currentPokemonPage() = currentPage
    fun loadPokemonPaginated() {


        viewModelScope.launch {
            _isLoading.postValue(true)
            val offset = currentPage * PAGE_SIZE
            Log.i(TAG, "loadPokemonPaginated(curPage: $currentPage, offset: $offset)")
            val result = repository.getPokemonList(PAGE_SIZE, offset)

            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "loadPokemonPaginated() Success")
                    result.data?.let { response ->
                        totalResults = response.count
                        val pokedexEntries = response.results.toPokedexListOfEntries()
                        val resultsLoaded = currentPage * PAGE_SIZE
                        Log.d(TAG, "loadPokemonPaginated   resultsLoaded: $resultsLoaded >= totalResults: $totalResults")
                        currentPage++
                        _loadError.postValue("")
                        _isLoading.postValue(false)

                        val currentList = _pokemonList.value
                        if (currentList == null) {
                            Log.d(TAG, "loadPokemonPaginated   first page")
                            _pokemonList.postValue(pokedexEntries)
                            Log.d(TAG, "loadPokemonPaginated   emiting: $pokedexEntries")
                        } else {
                            val tempList: List<PokedexListEntry> = currentList + pokedexEntries
                            Log.d(TAG, "loadPokemonPaginated   emiting: $tempList")
                            _pokemonList.postValue(tempList)
                        }
                    }
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