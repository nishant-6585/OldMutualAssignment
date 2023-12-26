package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation3 (

  @SerializedName("emerald"           ) var emerald           : Emerald?           = Emerald(),
  @SerializedName("firered-leafgreen" ) var fireredLeafgreen : FireredLeafgreen? = FireredLeafgreen(),
  @SerializedName("ruby-sapphire"     ) var rubySapphire     : RubySapphire?     = RubySapphire()

)