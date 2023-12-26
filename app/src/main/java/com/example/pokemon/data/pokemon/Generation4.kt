package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation4 (

  @SerializedName("diamond-pearl"        ) var diamondPearl        : DiamondPearl?        = DiamondPearl(),
  @SerializedName("heartgold-soulsilver" ) var heartgoldSoulsilver : HeartgoldSoulsilver? = HeartgoldSoulsilver(),
  @SerializedName("platinum"             ) var platinum             : Platinum?             = Platinum()

)