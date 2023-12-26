package com.example.pokemon.net

import com.example.pokemon.data.pokemon.PokemonData
import com.example.pokemon.data.PokemonDetailsData
import com.example.pokemon.data.PokemonListData
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET("/api/v2/ability/?limit=100&offset=100")
    suspend fun getPokemonListData(): PokemonListData

    @GET
    suspend fun getPokemonDetailsData(@Url url: String): PokemonDetailsData

    @GET
    suspend fun getPokemonDataDetails(@Url url: String): PokemonData
}