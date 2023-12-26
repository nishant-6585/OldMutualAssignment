package com.example.pokemon.data

import com.google.gson.annotations.SerializedName


data class Pokemon (
  @SerializedName("is_hidden" ) var isHidden : Boolean? = null,
  @SerializedName("pokemon"   ) var pokemon  : ResultData? = null,
  @SerializedName("slot"      ) var slot     : Int?     = null
)