package com.fabulouszanna.fabpokedex.repositories

import com.fabulouszanna.fabpokedex.data.PokemonDao
import com.fabulouszanna.fabpokedex.data.pokemon.Pokemon
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(private val dao: PokemonDao) :
    PokemonRepository {
    override fun getAllPokemon(): Flow<List<SchematicPokemon>> = dao.getAllPokemon().map { list ->
        list.map { pokemon ->
            SchematicPokemon(
                id = pokemon.id,
                name = pokemon.name,
                imgUrl = pokemon.imgUrl,
                type = pokemon.types[0]
            )
        }
    }

    override fun getPokemonById(id: Int): Flow<Pokemon> = dao.getPokemonById(id)
}