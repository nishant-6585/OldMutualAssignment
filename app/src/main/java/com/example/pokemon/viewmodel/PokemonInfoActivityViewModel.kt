package com.example.pokemon.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.pokemon.PokemonData
import com.example.pokemon.net.PokemonDataApiState
import com.example.pokemon.net.Status
import com.example.pokemon.repository.PokemonDataRepository
import com.example.pokemon.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoActivityViewModel @Inject constructor(
    private var pokemonDataRepository: PokemonDataRepository,
    application: Application
) : AndroidViewModel(application) {

    companion object {
        val TAG: String = PokemonInfoActivityViewModel::class.java.simpleName
    }

    val pokemonDataApiState = MutableStateFlow<PokemonDataApiState<PokemonData>>(
        PokemonDataApiState(
            Status.LOADING,
            null, ""
        )
    )

    fun getPokemonData(url: String?) {
        Log.d(MainActivityViewModel.TAG, "pokemon url is: $url")
        viewModelScope.launch {
            if (AppUtils.isNetworkConnected(getApplication())) {
                pokemonDataApiState.value = PokemonDataApiState.loading()
                Log.i(MainActivityViewModel.TAG, "Getting pokemon data from server")
                pokemonDataRepository.fetchPokemonDataDetails(url!!)
                    .catch {
                        it.printStackTrace()
                        Log.d(MainActivityViewModel.TAG, "${it.cause?.message}")
                        pokemonDataApiState.value =
                            PokemonDataApiState.error(it.message.toString())
                    }
                    .collect {
                        pokemonDataApiState.value = PokemonDataApiState.success(it.data)
                    }
            } else {
                Log.d(MainActivityViewModel.TAG, "No internet connection")
            }
        }
    }

}