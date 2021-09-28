package com.fabulouszanna.fabpokedex.features.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class EvolutionPokemon(
    @SerializedName("pokemon_id")
    val pokemonId: String,
    val name: String,
    @SerializedName("img_url")
    val imgUrl: String
)
