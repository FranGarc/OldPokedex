package com.garciafrancisco.pokedex.repository

import android.util.Log
import com.garciafrancisco.pokedex.data.remote.PokedexAPI
import com.garciafrancisco.pokedex.data.remote.RetrofitInstance
import com.garciafrancisco.pokedex.data.remote.responses.Pokemon
import com.garciafrancisco.pokedex.data.remote.responses.PokemonList
import com.garciafrancisco.pokedex.util.Resource

const val TAG = "PokedexRepository"

class PokedexRepository(
    private val api: PokedexAPI = RetrofitInstance.api
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        Log.d(TAG, "getPokemonList( limit: $limit,  offset: $offset ) ")

        lateinit var response: PokemonList
        try {
            response = api.getPokemonList(limit, offset)
            Log.d(TAG, "getPokemonList response: $response ")

        } catch (e: Exception) {
            Log.d(TAG, "getPokemonList Error: ${e.message} ")
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfoById(pokemonId: String): Resource<Pokemon> {
        Log.d(TAG, "getPokemonInfoById( $pokemonId ) ")

        lateinit var response: Pokemon
        try {
            response = api.getPokemonInfo(pokemonId)
            Log.d(TAG, "getPokemonInfoById response: $response ")

        } catch (e: Exception) {
            Log.d(TAG, "getPokemonInfoById Error: ${e.message} ")
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfoByName(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }
}