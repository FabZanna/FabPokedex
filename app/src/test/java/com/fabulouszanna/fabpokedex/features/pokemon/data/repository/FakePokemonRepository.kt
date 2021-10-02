package com.fabulouszanna.fabpokedex.features.pokemon.data.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.BaseStats
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository : PokemonRepository {
    private val pokemon1 = Pokemon(
        pokemonId = "1",
        name = "Bulbasaur",
        types = listOf("Grass", "Poison"),
        species = "",
        height = "",
        weight = "",
        imgUrl = "",
        hiddenAbility = "",
        abilities = emptyList(),
        baseStats = BaseStats(1,1,1,1,1,1,1),
        pokedexEntry = "",
        evolutions = emptyList(),
        moves = null
    )
    private val pokemon2 = Pokemon(
        pokemonId = "2",
        name = "Ivysaur",
        types = listOf("Grass", "Poison"),
        species = "",
        height = "",
        weight = "",
        imgUrl = "",
        hiddenAbility = "",
        abilities = emptyList(),
        baseStats = BaseStats(1,1,1,1,1,1,1),
        pokedexEntry = "",
        evolutions = emptyList(),
        moves = null
    )
    private val pokemon3 = Pokemon(
        pokemonId = "3",
        name = "Charmander",
        types = listOf("Fire"),
        species = "",
        height = "",
        weight = "",
        imgUrl = "",
        hiddenAbility = "",
        abilities = emptyList(),
        baseStats = BaseStats(1,1,1,1,1,1,1),
        pokedexEntry = "",
        evolutions = emptyList(),
        moves = null
    )
    private val pokemonList = listOf(pokemon1, pokemon2, pokemon3)

    override fun getAllPokemon(): Flow<List<Pokemon>> = flow {
        emit(pokemonList)
    }

    override fun getPokemonById(id: String): Flow<Pokemon> {
        TODO("Not yet implemented")
    }
}