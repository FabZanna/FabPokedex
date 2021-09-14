package com.fabulouszanna.fabpokedex.repositories

import com.fabulouszanna.fabpokedex.data.pokemon.Pokemon
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getAllPokemon(): Flow<List<SchematicPokemon>>
    fun getPokemonById(id: Int): Flow<Pokemon>
}