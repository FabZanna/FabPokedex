package com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases

import com.fabulouszanna.fabpokedex.features.pokemon.domain.model.PokemonCard
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {
    fun execute(
        filteredName: String = "",
        filteredType: String = ""
    ): Flow<List<PokemonCard>> =
        repository.getAllPokemon().map { list ->
            list
                .filter { pokemon ->
                    pokemon.name.lowercase().contains(filteredName.lowercase())
                }
                .filter { pokemon ->
                    filteredType == "" || filteredType in pokemon.types
                }
                .map {
                    it.toPokemonCard()
                }
        }
}