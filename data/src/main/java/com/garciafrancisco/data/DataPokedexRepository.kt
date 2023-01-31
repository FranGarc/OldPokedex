package com.garciafrancisco.data

import com.garciafrancisco.data.source.remote.PokedexRemoteDataSource
import com.garciafrancisco.data.source.remote.RetrofitPokedexDataSource
import com.garciafrancisco.data.source.remote.responses.Pokemon
import retrofit2.Response
import timber.log.Timber

const val TAG = "PokedexRepository"

class DataPokedexRepository(
    private val pokedexRemoteSource: PokedexRemoteDataSource = RetrofitPokedexDataSource.dataSource
) : PokedexRepository {


    suspend fun getPokemonInfoById(pokemonId: String): Response<Pokemon> {
        Timber.tag(TAG).d("getPokemonInfoById( " + pokemonId + " ) ")

        val response = pokedexRemoteSource.getPokemonInfo(pokemonId)
        Timber.tag(TAG).d("getPokemonInfoById response: " + response + " ")
        return response
    }

    override suspend fun getPokedexEntries(amountOfEntries: Int, startFromPokemonId: Int): List<DataPokedexListEntry> {
        Timber.tag(TAG).d("getPokemonList( limit: $amountOfEntries,  offset: $startFromPokemonId ")

        val response = pokedexRemoteSource.getPokemonList(amountOfEntries, startFromPokemonId)
        Timber.tag(TAG).d("getPokemonList response:$response ")
        val results = response.body()?.results
        return results?.map { DataPokedexListEntry(it.name, it.url) } ?: listOf<DataPokedexListEntry>()
    }
}
