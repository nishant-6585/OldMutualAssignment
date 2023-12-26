package com.example.pokemon.data

import com.example.pokemon.data.pokemon.VersionGroup
import com.google.gson.annotations.SerializedName


data class EffectChanges (

  @SerializedName("effect_entries" ) var effectEntries : ArrayList<EffectEntries> = arrayListOf(),
  @SerializedName("version_group"  ) var versionGroup  : VersionGroup?            = VersionGroup()

)