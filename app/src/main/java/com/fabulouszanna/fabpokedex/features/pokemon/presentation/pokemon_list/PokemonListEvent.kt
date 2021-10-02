package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

sealed class PokemonListEvent {
    data class FilterByName(val name: String): PokemonListEvent()
    data class FilterByType(val type: String): PokemonListEvent()
}
