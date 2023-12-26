package com.example.pokemon.data

import com.google.gson.annotations.SerializedName


data class Names (

  @SerializedName("language" ) var language : Language? = Language(),
  @SerializedName("name"     ) var name     : String?   = null

)