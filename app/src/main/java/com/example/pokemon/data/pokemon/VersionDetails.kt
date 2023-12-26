package com.example.pokemon.data.pokemon

import com.example.pokemon.data.pokemon.Version
import com.google.gson.annotations.SerializedName


data class VersionDetails (

  @SerializedName("rarity"  ) var rarity  : Int?     = null,
  @SerializedName("version" ) var version : Version? = Version()

)