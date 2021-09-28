package com.fabulouszanna.fabpokedex.features.pokemon.data.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.db.PokemonDao
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(private val dao: PokemonDao) : PokemonRepository {
    override fun getAllPokemon(): Flow<List<Pokemon>> = dao.getAllPokemon()
    override fun getPokemonById(id: String): Flow<Pokemon> = dao.getPokemonById(id)
}