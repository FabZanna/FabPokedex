package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

import com.fabulouszanna.fabpokedex.features.pokemon.domain.model.PokemonCard

data class PokemonListState(
    val pokemonList: List<PokemonCard> = emptyList(),
    val filteredName: String = "",
    val filteredType: String = "",
    val isListEmpty: Boolean = false
)
