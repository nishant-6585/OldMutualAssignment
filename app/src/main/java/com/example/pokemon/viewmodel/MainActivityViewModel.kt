package com.example.pokemon.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.PokemonDetailsData
import com.example.pokemon.data.PokemonListData
import com.example.pokemon.data.ResultData
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
class MainActivityViewModel @Inject constructor(
    private var pokemonDataRepository: PokemonDataRepository,
    application: Application
) : AndroidViewModel(application) {

    companion object {
        val TAG: String = MainActivityViewModel::class.java.simpleName
    }

    val pokemonListDataApiState = MutableStateFlow<PokemonDataApiState<PokemonListData>>(
        PokemonDataApiState(
            Status.LOADING,
            null, ""
        )
    )

    val getPokemonDataAPIState = MutableStateFlow<PokemonDataApiState<PokemonDetailsData>>(
        PokemonDataApiState(
            Status.LOADING,
            null, ""
        )
    )

    init {
        fetchPokemonListData()
    }

    private fun fetchPokemonListData() {
        viewModelScope.launch {
            if (AppUtils.isNetworkConnected(getApplication())) {
                pokemonListDataApiState.value = PokemonDataApiState.loading()
                Log.i(TAG, "Getting pokemon list data from server")
                pokemonDataRepository.fetchPokemonListData()
                    .catch {
                        it.printStackTrace()
                        pokemonListDataApiState.value =
                            PokemonDataApiState.error(it.message.toString())
                    }
                    .collect {
                        pokemonListDataApiState.value = PokemonDataApiState.success(it.data)
                    }
            } else {
                Log.d(TAG, "No internet connection")
            }
        }
    }

    fun fetchPokemonData(urlList: ArrayList<ResultData>) {
        for(item in urlList) {
            Log.d(TAG, "pokemon url is: ${item.url}")
            viewModelScope.launch {
                if (AppUtils.isNetworkConnected(getApplication())) {
                    getPokemonDataAPIState.value = PokemonDataApiState.loading()
                    Log.i(TAG, "Getting pokemon data from server")
                    pokemonDataRepository.fetchPokemonData(item.url)
                        .catch {
                            it.printStackTrace()
                            Log.d(TAG, "${it.cause?.message}")
                            getPokemonDataAPIState.value =
                                PokemonDataApiState.error(it.message.toString())

                        }
                        .collect {
                            getPokemonDataAPIState.value = PokemonDataApiState.success(it.data)
                        }
                } else {
                    Log.d(TAG, "No internet connection")
                }
            }
        }
    }
}