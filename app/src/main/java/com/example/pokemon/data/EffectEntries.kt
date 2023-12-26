package com.example.pokemon.data

import com.google.gson.annotations.SerializedName


data class EffectEntries (

  @SerializedName("effect"       ) var effect      : String?   = null,
  @SerializedName("language"     ) var language    : Language? = Language(),
  @SerializedName("short_effect" ) var shortEffect : String?   = null

)