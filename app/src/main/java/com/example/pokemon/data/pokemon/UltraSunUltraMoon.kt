package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class UltraSunUltraMoon (

  @SerializedName("front_default"      ) var frontDefault     : String? = null,
  @SerializedName("front_female"       ) var frontFemale      : String? = null,
  @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
  @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null

)