package com.example.pokemon.net

data class PokemonDataApiState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T?): PokemonDataApiState<T> {
            return PokemonDataApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): PokemonDataApiState<T> {
            return PokemonDataApiState(Status.ERROR, null, msg)
        }

        fun <T> loading(): PokemonDataApiState<T> {
            return PokemonDataApiState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}