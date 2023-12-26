package com.example.pokemon

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemon.databinding.ActivityInfoBinding
import com.example.pokemon.net.Status
import com.example.pokemon.viewmodel.PokemonInfoActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonInfoActivity: AppCompatActivity() {

    companion object {
        val TAG: String = PokemonInfoActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityInfoBinding
    private val viewModel: PokemonInfoActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        buildUI(intent.getStringExtra("url"), intent.getStringExtra("pokemonName"))
    }

    private fun buildUI(url: String?, pokemonName: String?) {
        lifecycleScope.launch {
            viewModel.getPokemonData(url)
            viewModel.pokemonDataApiState.collect {
                Log.d(TAG, "received pokemon data")
                when (it.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "pokemon data loading..")
                        Toast.makeText(
                            this@PokemonInfoActivity,
                            "Pokemon data is loading",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Status.SUCCESS -> {
                        Log.d(TAG, "Nishant - image URL: ${it.data!!.sprites?.frontDefault}")
                        // Use Glide to load the image from the URL
                        Glide.with(this@PokemonInfoActivity)
                            .load(it.data!!.sprites?.frontDefault)
                            .apply(
                                RequestOptions()
                                    .placeholder(R.drawable.place_holder) // Placeholder image
                                    .error(R.drawable.error_image) // Error image in case of loading failure
                            )
                            .into(binding.ivPokemonImage)
                        binding.tvPokemonName.text = it.data.name
                        binding.tvPokemonBaseStat.text = "Base stat: ${it.data.stats[0].baseStat.toString()}"
                        binding.tvPokemonEffort.text = "Effort: ${it.data.stats[0].effort.toString()}"
                    }
                    else -> {
                        Log.e(
                            TAG,
                            "error occurred while fetching pokemon data, message is : ${it.message}"
                        )
                        Toast.makeText(this@PokemonInfoActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}