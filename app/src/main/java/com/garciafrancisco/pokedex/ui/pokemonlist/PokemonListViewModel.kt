package com.garciafrancisco.pokedex.ui.pokemonlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garciafrancisco.pokedex.data.models.PokedexListEntry
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.util.Constants.PAGE_SIZE
import com.garciafrancisco.pokedex.util.Resource
import com.garciafrancisco.pokedex.util.extensions.pokeCapitalize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "PokemonListViewModel"

class PokemonListViewModel(private val repository: PokedexRepository = PokedexRepository()) :
    ViewModel() {


    private var curPage = 0

    private val _pokemonList: MutableLiveData<List<PokedexListEntry>> = MutableLiveData()
    val pokemonList: LiveData<List<PokedexListEntry>> = _pokemonList
    private val _loadError: MutableLiveData<String> = MutableLiveData()
    val loadError: LiveData<String> = _loadError
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _endReached: MutableLiveData<Boolean> = MutableLiveData()
    val endReached: LiveData<Boolean> = _endReached

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    private var _isSearching: MutableLiveData<Boolean> = MutableLiveData()
    var isSearching: LiveData<Boolean> = _isSearching

    init {
        Log.d(TAG, "init()")

        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonList.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                _pokemonList.postValue(cachedPokemonList)
                _isSearching.postValue(false)
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch?.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.pokeApiId.toString() == query.trim()
            }
            if (isSearchStarting) {
                pokemonList.value?.let { list ->
                    cachedPokemonList = list
                }

                isSearchStarting = false
            }
            results?.let { list ->
                _pokemonList.postValue(list)
            }

            _isSearching.postValue(true)
        }
    }

    fun loadPokemonPaginated() {
        Log.d(TAG, "loadPokemonPaginated()")

        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "loadPokemonPaginated() Success")

                    _endReached.postValue(curPage * PAGE_SIZE >= result.data!!.count)
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val pokeapiID = extractIdFromUrl(entry.url)
                        val imageUrl =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeapiID}.png"
                        PokedexListEntry(entry.name.pokeCapitalize(), imageUrl, pokeapiID.toInt())
                    }
                    curPage++

                    _loadError.postValue("")
                    _isLoading.postValue(false)

                        /* no-op */
                    val currentList = _pokemonList.value
                    if (currentList == null) {
                        _pokemonList.postValue(pokedexEntries)
                    } else {
                        val tempList: List<PokedexListEntry> = currentList + pokedexEntries
                        _pokemonList.postValue(tempList)
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

    private fun extractIdFromUrl(url: String): String {
        return if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }
    }

//    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
//        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
//
//        Palette.from(bmp).generate { palette ->
//            palette?.dominantSwatch?.rgb?.let { colorValue ->
//                onFinish(colorValue)
//            }
//        }
//    }


}