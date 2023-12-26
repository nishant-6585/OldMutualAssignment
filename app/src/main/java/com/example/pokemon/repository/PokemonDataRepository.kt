package com.example.pokemon.repository

import android.util.Log
import com.example.pokemon.data.pokemon.PokemonData
import com.example.pokemon.data.PokemonDetailsData
import com.example.pokemon.data.PokemonListData
import com.example.pokemon.net.Api
import com.example.pokemon.net.PokemonDataApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
    private var apiService: Api
) {

    companion object {
        val TAG: String = PokemonDataRepository::class.java.simpleName
    }

    suspend fun fetchPokemonListData(): Flow<PokemonDataApiState<PokemonListData>> {

        return flow {
            val data = apiService.getPokemonListData()
            Log.d(TAG, "data size: ${data.count}")
            emit(PokemonDataApiState.success(data))
        }.flowOn(Dispatchers.IO)

    }

    suspend fun fetchPokemonData(url: String): Flow<PokemonDataApiState<PokemonDetailsData>> {
        return flow {
            val data = apiService.getPokemonDetailsData(url)
            Log.d(TAG, "Pokemon name: ${data.name}")
            emit(PokemonDataApiState.success(data))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchPokemonDataDetails(url: String): Flow<PokemonDataApiState<PokemonData>> {
        return flow {
            val data = apiService.getPokemonDataDetails(url)
            Log.d(TAG, "Pokemon image url: ${data.sprites?.frontDefault}")
            emit(PokemonDataApiState.success(data))
        }.flowOn(Dispatchers.IO)
    }
}