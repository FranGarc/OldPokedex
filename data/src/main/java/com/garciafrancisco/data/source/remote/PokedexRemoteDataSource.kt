package com.garciafrancisco.data.source.remote

import com.garciafrancisco.data.source.remote.responses.Pokemon
import com.garciafrancisco.data.source.remote.responses.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexRemoteDataSource {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") amountOfEntries: Int,
        @Query("offset") startFromPokemonId: Int
    ): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Response<Pokemon>
}