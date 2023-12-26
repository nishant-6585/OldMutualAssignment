package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation1 (

  @SerializedName("red-blue" ) var redBlue : RedBlue? = RedBlue(),
  @SerializedName("yellow"   ) var yellow   : Yellow?   = Yellow()

)