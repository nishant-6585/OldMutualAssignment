package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Versions (

  @SerializedName("generation-i"    ) var generation1    : Generation1?    = Generation1(),
  @SerializedName("generation-ii"   ) var generation2   : Generation2?   = Generation2(),
  @SerializedName("generation-iii"  ) var generation3  : Generation3?  = Generation3(),
  @SerializedName("generation-iv"   ) var generation4   : Generation4?   = Generation4(),
  @SerializedName("generation-v"    ) var generation5    : Generation5?    = Generation5(),
  @SerializedName("generation-vi"   ) var generation6   : Generation6?   = Generation6(),
  @SerializedName("generation-vii"  ) var generation7  : Generation7?  = Generation7(),
  @SerializedName("generation-viii" ) var generation8 : Generation8? = Generation8()

)