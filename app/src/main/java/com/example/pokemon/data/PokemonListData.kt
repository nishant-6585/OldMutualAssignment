package com.example.pokemon.data

import com.google.gson.annotations.SerializedName

data class PokemonListData(
    @SerializedName("count") var count: Int,
    @SerializedName("next") var next: String,
    @SerializedName("previous") var previous: String,
    @SerializedName("results") var results: ArrayList<ResultData>
)

data class ResultData(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)