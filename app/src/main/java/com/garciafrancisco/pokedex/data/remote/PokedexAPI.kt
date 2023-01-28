package com.garciafrancisco.pokedex.data.remote

import com.garciafrancisco.pokedex.data.remote.responses.Pokemon
import com.garciafrancisco.pokedex.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexAPI {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") amountOfEntries: Int,
        @Query("offset") startFromPokemonId: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon
}