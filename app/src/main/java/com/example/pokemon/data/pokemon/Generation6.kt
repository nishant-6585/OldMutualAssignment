package com.example.pokemon.data.pokemon

import com.google.gson.annotations.SerializedName


data class Generation6 (

  @SerializedName("omegaruby-alphasapphire" ) var omegarubyAlphasapphire : OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
  @SerializedName("x-y"                     ) var xy                     : XY?                     = XY()

)