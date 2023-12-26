package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation5 (

  @SerializedName("black-white" ) var blackWhite : BlackWhite? = BlackWhite()

)