package com.fabulouszanna.fabpokedex.features.pokemon.data.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.BaseStats
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository : PokemonRepository {
    private val pokemon1 = Pokemon(
        id = 0,
        pokemonId = "#001",
        name = "Bulbasaur",
        types = listOf("Grass", "Poison"),
        species = "Seed Pokemon",
        height = "xx",
        weight = "xx",
        imgUrl = "xx",
        hiddenAbility = "",
        abilities = listOf("1", "2"),
        baseStats = BaseStats(1, 1, 1, 1, 1, 1, 1),
        pokedexEntry = "xx",
        evolutions = emptyList(),
        moves = null
    )

    private val pokemon2 = Pokemon(
        id = 1,
        pokemonId = "#002",
        name = "Ivysaur",
        types = listOf("Grass", "Poison"),
        species = "Seed Pokemon",
        height = "xx",
        weight = "xx",
        imgUrl = "xx",
        hiddenAbility = "",
        abilities = listOf("1", "2"),
        baseStats = BaseStats(1, 1, 1, 1, 1, 1, 1),
        pokedexEntry = "xx",
        evolutions = emptyList(),
        moves = null
    )

    override fun getAllPokemon(): Flow<List<Pokemon>> = flow {
        emit(listOf(pokemon1, pokemon2))
    }

    override fun getPokemonByName(name: String): Flow<Pokemon> {
        TODO("Not yet implemented")
    }
}