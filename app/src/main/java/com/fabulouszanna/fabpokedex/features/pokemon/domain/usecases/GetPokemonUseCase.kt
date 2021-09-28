package com.fabulouszanna.fabpokedex.features.pokemon.domain.usecases

import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    fun execute(id: String) = repository.getPokemonById(id)
}