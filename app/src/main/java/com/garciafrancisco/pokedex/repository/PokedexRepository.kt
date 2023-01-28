package com.garciafrancisco.pokedex.repository

import android.util.Log
import com.garciafrancisco.pokedex.data.remote.PokedexAPI
import com.garciafrancisco.pokedex.data.remote.RetrofitInstance
import com.garciafrancisco.pokedex.data.remote.responses.Pokemon
import com.garciafrancisco.pokedex.data.remote.responses.PokemonList
import com.garciafrancisco.pokedex.util.Resource
import timber.log.Timber

const val TAG = "PokedexRepository"

class PokedexRepository(
    private val api: PokedexAPI = RetrofitInstance.api
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        Timber.tag(TAG).d("getPokemonList( limit: $limit,  offset: $offset ")

        lateinit var response: PokemonList
        try {
            response = api.getPokemonList(limit, offset)
            Timber.tag(TAG).d("getPokemonList response:$response ")

        } catch (e: Exception) {
            Timber.tag(TAG).d("getPokemonList Error: " + e.message + " ")
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfoById(pokemonId: String): Resource<Pokemon> {
        Timber.tag(TAG).d("getPokemonInfoById( " + pokemonId + " ) ")

        lateinit var response: Pokemon
        try {
            response = api.getPokemonInfo(pokemonId)
            Timber.tag(TAG).d("getPokemonInfoById response: " + response + " ")

        } catch (e: Exception) {
            Timber.tag(TAG).d("getPokemonInfoById Error: " + e.message + " ")
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }

}