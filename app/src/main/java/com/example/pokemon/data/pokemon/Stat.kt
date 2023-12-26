package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Stat (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("url"  ) var url  : String? = null

)