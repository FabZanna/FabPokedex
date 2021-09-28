package com.fabulouszanna.fabpokedex.features.pokemon.domain.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getAllPokemon(): Flow<List<Pokemon>>
    fun getPokemonById(id: String): Flow<Pokemon>
}