package com.example.pokemon.data

import com.example.pokemon.data.pokemon.VersionGroup
import com.google.gson.annotations.SerializedName


data class FlavorTextEntries (

  @SerializedName("flavor_text"   ) var flavorText   : String?       = null,
  @SerializedName("language"      ) var language     : Language?     = Language(),
  @SerializedName("version_group" ) var versionGroup : VersionGroup? = VersionGroup()

)