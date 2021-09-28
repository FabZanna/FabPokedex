package com.fabulouszanna.fabpokedex.features.pokemon.domain.usecases

import com.fabulouszanna.fabpokedex.features.pokemon.data.repository.FakePokemonRepository
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {

    private lateinit var pokemonList: GetPokemonListUseCase
    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = FakePokemonRepository()
        pokemonList = GetPokemonListUseCase(repository)
    }

    @Test
    fun `fetching data from repository`() = runBlocking {
        val pokemon = pokemonList.execute().toList()
        print(pokemon)
        assertThat(pokemon[0].size).isEqualTo(2)
    }
}