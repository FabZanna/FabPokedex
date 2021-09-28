package com.fabulouszanna.fabpokedex.features.pokemon.data.model

data class Evolution(
    val from: EvolutionPokemon,
    val how: String,
    val to: EvolutionPokemon,
)