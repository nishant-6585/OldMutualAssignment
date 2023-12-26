package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class OfficialArtwork (

  @SerializedName("front_default" ) var frontDefault : String? = null,
  @SerializedName("front_shiny"   ) var frontShiny   : String? = null

)