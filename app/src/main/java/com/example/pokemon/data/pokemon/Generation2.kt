package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation2 (

  @SerializedName("crystal" ) var crystal : Crystal? = Crystal(),
  @SerializedName("gold"    ) var gold    : Gold?    = Gold(),
  @SerializedName("silver"  ) var silver  : Silver?  = Silver()

)