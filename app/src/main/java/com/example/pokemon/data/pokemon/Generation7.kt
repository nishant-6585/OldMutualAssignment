package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation7 (

  @SerializedName("icons"                ) var icons                : Icons?                = Icons(),
  @SerializedName("ultra-sun-ultra-moon" ) var ultraSunUltraMoon : UltraSunUltraMoon? = UltraSunUltraMoon()

)