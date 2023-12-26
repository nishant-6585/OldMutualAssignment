package com.example.pokemon.data

import com.google.gson.annotations.SerializedName


data class PokemonDetailsData (

    @SerializedName("effect_changes"      ) var effectChanges     : ArrayList<EffectChanges>     = arrayListOf(),
    @SerializedName("effect_entries"      ) var effectEntries     : ArrayList<EffectEntries>     = arrayListOf(),
    @SerializedName("flavor_text_entries" ) var flavorTextEntries : ArrayList<FlavorTextEntries> = arrayListOf(),
    @SerializedName("generation"          ) var generation        : Generation?                  = Generation(),
    @SerializedName("id"                  ) var id                : Int?                         = null,
    @SerializedName("is_main_series"      ) var isMainSeries      : Boolean?                     = null,
    @SerializedName("name"                ) var name              : String?                      = null,
    @SerializedName("names"               ) var names             : ArrayList<Names>             = arrayListOf(),
    @SerializedName("pokemon"             ) var pokemon           : ArrayList<Pokemon>           = arrayListOf()

)