package com.fabulouszanna.fabpokedex.features.pokemon.domain.usecases

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {
    fun execute(): Flow<List<Pokemon>> = repository.getAllPokemon()
}